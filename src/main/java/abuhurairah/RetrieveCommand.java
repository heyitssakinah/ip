package abuhurairah;

import java.util.ArrayList;

public class RetrieveCommand {

    /**
     * Prints all tasks stored in the task list.
     */
    public static String printList(ArrayList<Task> tasks) {
        String storedList = "";
        for (int i = 0; i < tasks.size(); i++) {
            storedList += " " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return storedList;
    }

    public static String getOverdueTask(String reqArgsString, ArrayList<Task> tasks) {
        if (reqArgsString.equalsIgnoreCase("OVERDUE")) {
            String response = "Here are the OVERDUE tasks :)";
            for (Task overdueTask : tasks) {
                if(overdueTask instanceof Deadline) {
                    if(overdueTask.isOverdue() && !overdueTask.isComplete()) {
                        response += overdueTask.toString();
                    }
                }
            }
            return response;
        }
        return "get - [Overdue] ? ";
    }
    public static String listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return"No new tasks, YAY";
        }
        return"That's a lot of things to do...\n" + printList(tasks);
    }

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
