package abuhurairah;

import java.util.ArrayList;


public class AddCommand {

    public static void addTask(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
    }

    public static String addDeadline(String reqArgsString, ArrayList<Task> tasks) {
        Task task = new Deadline(reqArgsString.split("/by")[0], reqArgsString.split("/by")[1]);
        tasks.add(task);
        return Ui.serveRequestBack("Sure thing, I've added this task:", task, 0);
    }

    public static String addEvent(String reqArgsString, ArrayList<Task> tasks) {
        Task task = new Event(reqArgsString.split("/from")[0], reqArgsString.split("/from")[1]
                .split("/to")[0],
                reqArgsString.split("/to")[1]);
        tasks.add(task);
        String response = Ui.serveRequestBack("Sure thing, I've added this task:", task, 0);
        return response;
    }

    public static String addTodo(String reqArgsString, ArrayList<Task> tasks) {
        Task task = new Todo(reqArgsString);
        tasks.add(task);
        return Ui.serveRequestBack("Sure thing, I've added this task:", task, 0);
    }
}
