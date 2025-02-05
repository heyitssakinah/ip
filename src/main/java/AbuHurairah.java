import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class AbuHurairah {
    public static Ui ui;
    public static Storage storage;
    public static Parser parser;
    private static String NAME = "Abu Hurairah";

    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage("./AbuHurairahHistory.txt");
        parser = new Parser();
        int unDoneCount = 0;
        TaskList taskList = new TaskList();

        // Get history
        unDoneCount = storage.getStore("./AbuHurairahHistory.txt", taskList, NAME);
        if (!taskList.getTasks().isEmpty()) {
            ui.seperator();
            taskList.printStoredList();
            ui.showWelcomeBack(NAME);
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
