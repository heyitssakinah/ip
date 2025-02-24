package abuhurairah.command;

import java.util.ArrayList;

import abuhurairah.task.Task;
import abuhurairah.task.TaskTracker;
import abuhurairah.ui.Ui;

/**
 * Updates list according to completed tasks
 */
public class MarkCommand {
    /**
     * Marks a task as completed in the task list.
     *
     * This method retrieves the task at the specified index, checks if it is already marked
     * as complete, and if not, marks it as complete. The method also updates the TaskTracker
     * to reflect the completion of a task and returns a confirmation message.
     *
     * @param reqArgsString The string containing the index of the task to be marked as completed.
     * @param tasks The list of tasks where the task will be marked as completed.
     * @param taskTracker The TaskTracker instance to update the count of remaining tasks.
     * @return A string response confirming the task has been marked as completed, or an error message
     *         if the task was already completed.
     */
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

    /**
     * Unmarks a task as not completed in the task list.
     *
     * This method retrieves the task at the specified index, checks if it is already marked
     * as incomplete, and if not, unmarks it as complete. The method also updates the TaskTracker
     * to reflect the change and returns a confirmation message.
     *
     * @param reqArgsString The string containing the index of the task to be unmarked.
     * @param tasks The list of tasks where the task will be unmarked as completed.
     * @param taskTracker The TaskTracker instance to update the count of remaining tasks.
     * @return A string response confirming the task has been unmarked, or an error message
     *         if the task was already unmarked.
     */
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
