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

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param path The file path where tasks will be stored.
     */
    public Storage(String path) {
        this.path = path;
        this.parser = new Parser();
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
        assert taskList != null : "TaskList cannot be null";
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Task task : taskList.getTasks()) {
                assert task != null : "Task cannot be null";
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.error(e);
        }
    }

    /**
     * Loads tasks from the file into the provided TaskList.
     * Parses each line using the Parser and updates the task list.
     *
     * @param taskList The TaskList to store the retrieved tasks.
     * @return The count of tasks that are not yet completed.
     */
    public void getStore(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        try {
            int taskCount = 0;
            File f = getFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                taskCount++;
                parser.textToArrayList(sc.nextLine(), taskList, taskCount);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Storage file not found at " + path);
            e.printStackTrace(); // Debugging purpose
        }
    }

    public String getPath() {
        return this.path;
    }
}
