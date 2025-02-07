package abuhurairah;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Parser
 * class is responsible for processing text input
 * and converting it into structured data. It assists in parsing stored
 * tasks and user commands.
 */
public class Parser {

    /**
     * Constructs a Parser
     * object with an instance of Ui}.
     */
    public Parser() {
    }

    /**
     * Extracts arguments from a user command by removing the command keyword
     * and returning the remaining part as a string.
     *
     * @param requestArgsArr An array containing the command and its arguments.
     * @return A string containing the extracted arguments.
     */
    public String getArgs(String[] requestArgsArr) {
        return String.join(" ", Arrays.asList(requestArgsArr).
                subList(1, requestArgsArr.length));
    }

    /**
     * Parses a string argument and converts it into an integer representing
     * the index of a task to be marked or unmarked.
     *
     * @param reqArgsString The string containing the task index.
     * @return The integer representation of the task index.
     */
    public int getIntToMark(String reqArgsString) {
        return Integer.parseInt(reqArgsString);
    }

    /**
     * Converts a line of text from the storage file into a task and adds it to the TaskList.
     *
     * @param s           The text line representing a task.
     * @param taskList    The TaskList where the task will be stored.
     * @param unDoneCount The number of incomplete tasks.
     * @param taskCount   The current task count.
     * @return The updated count of incomplete tasks.
     */
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