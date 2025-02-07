package abuhurairah;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String path;
    Parser parser;
    Ui ui;

    public Storage(String path) {
        this.path = path;
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public File getFile() {
        return new File(path);
    }

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
        } catch (FileNotFoundException e) { // Start a new chat Bot
            ui.showWelcome(name);
        }
        return 0;
    }
}