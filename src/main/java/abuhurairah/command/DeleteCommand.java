package abuhurairah.command;

import abuhurairah.task.Task;
import abuhurairah.task.TaskTracker;
import abuhurairah.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand {
    public static String deleteTask(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        int index = Integer.parseInt(reqArgsString);
        Task task = tasks.get(index - 1);
        if (task.isComplete()) {
            taskTracker.removeCompletedTask();
        }
        tasks.remove(index - 1);
        return Ui.serveRequestBack("OKIE DELETED THIS TASK:", task, 0);
    }
}
