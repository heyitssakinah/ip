package abuhurairah;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles reading from and writing to a file.
 * It is responsible for storing and retrieving tasks from persistent storage.
 */
public class Storage {
    private final String path;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param path The file path where tasks will be stored.
     */
    public Storage(String path) {
        this.path = path;
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Retrieves the file associated with the given storage path.
     *
     * @return The File object representing the storage file.
     */
    public File getFile() {
        return new File(path);
    }

    /**
     * Stores the tasks from the provided TaskList into the file.
     * Each task is written as a string to the file.
     *
     * @param taskList The TaskList containing tasks to be stored.
     */
    public void store(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Task task : taskList.getTasks()) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            ui.error(e);
        }
    }

    /**
     * Loads tasks from the file into the provided TaskList.
     * Parses each line using the Parser and updates the task list.
     *
     * @param path     The file path to read tasks from.
     * @param taskList The TaskList to store the retrieved tasks.
     * @param name     The name of the user (used for welcome message if the file is missing).
     * @return The count of tasks that are not yet completed.
     */
    public int getStore(String path, TaskList taskList, String name) {
        try {
            int unDoneCount = 0;
            int taskCount = 1;
            File f = getFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                unDoneCount = parser.textToArrayList(sc.nextLine(), taskList, unDoneCount, taskCount);
            }
            return unDoneCount;
        } catch (FileNotFoundException e) {
            // Start a new chat Bot if file does not exist
            ui.showWelcome(name);
        }
        return 0;
    }

    public String getPath() {
        return this.path;
    }
}
