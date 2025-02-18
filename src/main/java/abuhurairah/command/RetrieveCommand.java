package abuhurairah.command;

import java.util.ArrayList;

import abuhurairah.task.Deadline;
import abuhurairah.task.Task;

/**
 * Handles inputs where retrieving data from the list is required
 */
public class RetrieveCommand {

    /**
     * Prints all tasks stored in the task list.
     *
     * @param tasks The list of tasks to be printed.
     * @return A string representation of all tasks in the task list.
     */
    public static String printList(ArrayList<Task> tasks) {
        String storedList = "";
        for (int i = 0; i < tasks.size(); i++) {
            storedList += " " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return storedList;
    }

    /**
     * Retrieves and prints overdue tasks from the task list.
     *
     * @param reqArgsString The request argument string, used to identify the "OVERDUE" keyword.
     * @param tasks The list of tasks to search for overdue tasks.
     * @return A string containing the overdue tasks, or a message asking for valid input.
     */
    public static String getOverdueTask(String reqArgsString, ArrayList<Task> tasks) {
        if (reqArgsString.equalsIgnoreCase("OVERDUE")) {
            String response = "Here are the OVERDUE tasks :)";
            for (Task overdueTask : tasks) {
                if (overdueTask instanceof Deadline) {
                    if (overdueTask.isOverdue() && !overdueTask.isComplete()) {
                        response += overdueTask.toString();
                    }
                }
            }
            return response;
        }
        return "get - [Overdue] ? ";
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return A string indicating either no tasks or a formatted list of tasks.
     */
    public static String listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No new tasks, YAY";
        }
        return "That's a lot of things to do...\n" + printList(tasks);
    }

    /**
     * Searches for tasks by description.
     *
     * @param reqArgsString The request argument string, where the first word is the search term.
     * @param tasks The list of tasks to search within.
     * @return A string containing the matching tasks or a message indicating no matches were found.
     */
    public static String findTask(String reqArgsString, ArrayList<Task> tasks) {
        String searchItem = reqArgsString.split(" ")[0];
        boolean found = false;
        String response = "Searching for your item....\n";
        for (Task taskItem : tasks) {
            if (taskItem.getDescription().toLowerCase().contains(searchItem.toLowerCase())) {
                response = response + taskItem.toString() + "\n";
                found = true;
            }
        }
        if (!found) {
            return "no such item!!!";
        }
        return response;
    }
}
