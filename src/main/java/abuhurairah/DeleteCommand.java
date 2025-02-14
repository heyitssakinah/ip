package abuhurairah;

import java.util.ArrayList;

public class DeleteCommand {
    public static String deleteTask(String reqArgsString, ArrayList<Task> tasks) {
        int index = Integer.parseInt(reqArgsString);
        Task task = tasks.get(index - 1);
        if (!task.isComplete()) {
        }
        tasks.remove(index - 1);
        return Ui.serveRequestBack("OKIE DELETED THIS TASK:", task, 0);
    }
}
