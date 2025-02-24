package abuhurairah.command;

import java.util.ArrayList;

import abuhurairah.task.Task;
import abuhurairah.task.TaskTracker;
import abuhurairah.ui.Ui;

/**
 * Handles input to delete tasks from the list
 */
public class DeleteCommand {

    /**
     * Deletes a task from the task list.
     *
     * This method retrieves the task at the specified index and checks if it has been marked
     * as complete. If it is complete, the method updates the TaskTracker to remove the task
     * from the completed tasks count. The task is then removed from the task list.
     *
     * @param reqArgsString The string containing the index of the task to be deleted.
     * @param tasks The list of tasks from which the task will be deleted.
     * @param taskTracker The TaskTracker instance to update the completed tasks count.
     * @return A string response confirming the task has been deleted, along with the task details.
     */
    public static String deleteTask(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        int index = Integer.parseInt(reqArgsString);
        Task task = tasks.get(index - 1);
        if (task.isComplete()) {
            taskTracker.removeCompletedTask();
        }
        tasks.remove(index - 1);
        return Ui.serveRequestBack("OKIE DELETED THIS TASK:", task, taskTracker.getRemainingTasks(tasks.size()));
    }
}
