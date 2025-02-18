package abuhurairah;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RetrieveCommand {

    /**
     * Prints all tasks stored in the task list.
     */
    public static String printList(ArrayList<Task> tasks) {
        return tasks.stream()
                .map(task -> " " + (tasks.indexOf(task) + 1) + "." + task.toString() + "\n")
                .collect(Collectors.joining("\n"));
    }

    public static String getOverdueTask(String reqArgsString, ArrayList<Task> tasks) {
        if (reqArgsString.equalsIgnoreCase("OVERDUE")) {
            List<Task> overdueTasks = tasks.stream()
                    .filter(task -> task instanceof Deadline)
                    .filter(task ->task.isOverdue() && !task.isComplete())
                    .toList();

            if (overdueTasks.isEmpty()) {
                return "No overdue tasks.";
            }

            String overdueList = overdueTasks.stream()
                    .map(Task::toString)
                    .collect(Collectors.joining("\n"));

            return "Here are the OVERDUE tasks :)\n" + overdueList;
        }
        return "Wrong Format: get [overdue]";
    }

    public static String listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return"No new tasks, YAY";
        }
        return"That's a lot of things to do...\n" + printList(tasks);
    }

    public static String findTask(String reqArgsString, ArrayList<Task> tasks) {
        String searchItem = reqArgsString.split(" ")[0];
        String response = "Searching for your item....\n";
        String foundTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(searchItem.toLowerCase()))
                .map(Task::toString)
                .collect(Collectors.joining("\n"));

        if (foundTasks.trim().isEmpty()) {
            return "no such item!!!";
        }
        return response;
    }
}
