import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private Ui ui;

    public Parser() {
        this.ui = new Ui();
    }

    public String getArgs(String[] requestArgsArr) {
        return String.join(" ", Arrays.asList(requestArgsArr).
                subList(1, requestArgsArr.length));
    }

    public int getIntToMark(String reqArgsString) {
        return Integer.parseInt(reqArgsString);
    }

    public int textToArrayList(String s, TaskList taskList, int unDoneCount, int taskCount) {
        String cleanedString = s.replaceAll("\\[|\\(|\\)", "").trim();
        cleanedString = cleanedString.replaceAll("\\]", " ").trim();
        cleanedString = cleanedString.replaceAll("from:", "/from");
        cleanedString = cleanedString.replaceAll("to:", "/to");
        cleanedString = cleanedString.replaceAll("by:", "/by");
        cleanedString = cleanedString.replaceAll("E", "Event");
        cleanedString = cleanedString.replaceAll("D", "Deadline");
        cleanedString = cleanedString.replaceAll("T", "Todo");
        if (cleanedString.split(" ")[1].equals("X")) {
            cleanedString = cleanedString.replaceAll("X ", "");
            cleanedString = cleanedString.replaceAll("  ", " ");
            unDoneCount = taskList.argumentHandling(cleanedString, unDoneCount, false);
            taskCount++;
            unDoneCount = taskList.argumentHandling("MARK" + String.valueOf(taskCount),
                    unDoneCount, false);
        } else {
            cleanedString = cleanedString.replaceAll("    ", " ");
            unDoneCount = taskList.argumentHandling(cleanedString, unDoneCount, false);
            taskCount++;
        }
        return unDoneCount;
    }


}