package abuhurairah;

import java.util.ArrayList;

public class MarkCommand {

    public static String markTask(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        int index = Integer.parseInt(reqArgsString);
        Task task = tasks.get(index - 1);
        if (task.isComplete()) {
            return "Task already marked as completed";
        } else {
            task.setComplete(true);
            taskTracker.addCompletedTask();
            return Ui.serveRequestBack("Alhamdulillah one down", task, taskTracker.getRemainingTasks(tasks.size()));
        }
    }

    public static String unMarkTask(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        int index = Integer.parseInt(reqArgsString);
        Task task = tasks.get(index - 1);
        if (!task.isComplete()) {
            return "Task already marked as not completed";
        } else {
            task.setComplete(false);
            taskTracker.removeCompletedTask();
            return Ui.serveRequestBack("Verily with hardship comes ease", task,
                    taskTracker.getRemainingTasks(tasks.size()));
        }
    }
}
