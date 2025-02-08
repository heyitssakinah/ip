package abuhurairah;

import java.util.Scanner;

/**
 * The main class for the AbuHurairah task management application.
 * This program stores and retrieves tasks, allowing users to interact
 * via a command-line interface.
 */
public class AbuHurairah {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage;
        Parser parser;
        String name = "Abu Hurairah";

        storage = new Storage("./AbuHurairahHistory.txt");
        parser = new Parser();
        int unDoneCount = 0;
        TaskList taskList = new TaskList();

        // Get history
        unDoneCount = storage.getStore("./AbuHurairahHistory.txt", taskList, name);
        if (!taskList.getTasks().isEmpty()) {
            ui.separator();
            taskList.printStoredList();
            ui.showWelcomeBack(name);
        }

        // Get request
        Scanner input = new Scanner(System.in);
        while (true) {
            if (!input.hasNextLine()) {
                break;
            }
            String request = input.nextLine();
            if (taskList.isBye(request)) {
                if (!taskList.isEmpty()) {
                    storage.store(taskList);
                }
                ui.bye();
                break;
            }
            unDoneCount = taskList.argumentHandling(request, unDoneCount, true);
        }
    }
}
