package abuhurairah.command;

import abuhurairah.task.*;
import abuhurairah.ui.Ui;

import java.util.ArrayList;


public class AddCommand {

    /**
     * Adds a general task to the task list.
     *
     * @param task The task to be added to the list.
     * @param tasks The list of tasks where the task will be added.
     */
    public static void addTask(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * This method parses the given string for the task description and deadline,
     * creates a new Deadline object, and adds it to the task list. It returns a
     * confirmation message along with the updated task count.
     *
     * @param reqArgsString The string containing the task description and deadline,
     *                       in the format "description /by deadline".
     * @param tasks The list of tasks where the task will be added.
     * @param taskTracker The TaskTracker instance to retrieve the remaining tasks count.
     * @return A string response confirming the task addition and remaining tasks count.
     */
    public static String addDeadline(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        Task task = new Deadline(reqArgsString.split("/by")[0], reqArgsString.split("/by")[1]);
        tasks.add(task);
        return Ui.serveRequestBack("Sure thing, I've added this task:", task,
                taskTracker.getRemainingTasks(tasks.size()));
    }

    /**
     * Adds an Event task to the task list.
     *
     * This method parses the given string for the task description, start time,
     * and end time, creates a new Event object, and adds it to the task list.
     * It returns a confirmation message along with the updated task count.
     *
     * @param reqArgsString The string containing the task description, start time,
     *                       and end time, in the format "description /from start /to end".
     * @param tasks The list of tasks where the task will be added.
     * @param taskTracker The TaskTracker instance to retrieve the remaining tasks count.
     * @return A string response confirming the task addition and remaining tasks count.
     */
    public static String addEvent(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        Task task = new Event(reqArgsString.split("/from")[0], reqArgsString.split("/from")[1]
                .split("/to")[0],
                reqArgsString.split("/to")[1]);
        tasks.add(task);
        String response = Ui.serveRequestBack("Sure thing, I've added this task:", task,
                taskTracker.getRemainingTasks(tasks.size()));
        return response;
    }

    /**
     * This method creates a new Todo task from the provided string and adds it
     * to the task list. It returns a confirmation message and updates the
     * task count.
     *
     * @param reqArgsString The string containing the task description.
     * @param tasks The list of tasks where the task will be added.
     * @param taskTracker The TaskTracker instance to retrieve the remaining tasks count.
     * @return A string response confirming the task addition and remaining tasks count.
     */
    public static String addTodo(String reqArgsString, ArrayList<Task> tasks, TaskTracker taskTracker) {
        Task task = new Todo(reqArgsString);
        tasks.add(task);
        return Ui.serveRequestBack("Sure thing, I've added this task:", task,
                taskTracker.getRemainingTasks(tasks.size()));
    }
}
