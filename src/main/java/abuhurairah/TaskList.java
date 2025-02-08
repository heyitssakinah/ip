package abuhurairah;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It allows adding, retrieving, and modifying tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Enumeration of valid request types.
     */
    private static enum Req {
        MARK, UNMARK, EVENT, DEADLINE, TODO, LIST, DELETE, BYE, GET, FIND;
    }

    /**
     * Checks if the given request is a "bye" command.
     *
     * @param request The input request.
     * @return true if the request is "bye", otherwise fals}.
     */
    public boolean isBye(String request) {
        return request.equalsIgnoreCase(Req.BYE.toString());
    }

    /**
     * Retrieves all tasks stored in the list.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the given request is a "list" command.
     *
     * @param request The input request.
     * @return true if the request is "list", otherwise fals}.
     */
    boolean isList(String request) {
        return request.equalsIgnoreCase(Req.LIST.toString());
    }

    /**
     * Prints all tasks stored in the task list.
     */
    public void printStoredList() {
        for (int i = 0; i < tasks.size(); i++) {
            ui.serve(" " + (i + 1) + "." + tasks.get(i).toString());
        }
        ui.separator();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, otherwise fals}.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Handles user input for task-related commands such as adding, marking, unmarking, deleting,
     * and listing tasks. It also handles overdue task retrieval.
     *
     * @param taskString  The raw user input string.
     * @param unDoneCount The number of incomplete tasks before the command.
     * @param isPrintable A flag indicating if messages should be printed.
     * @return The updated count of incomplete tasks.
     */
    public int argumentHandling(String taskString, int unDoneCount, boolean isPrintable) {
        ui.line(isPrintable);
        Task task;
        String[] requestArgsArr = taskString.split(" ");
        String reqType = requestArgsArr[0];
        String reqArgsString = parser.getArgs(requestArgsArr);
        try {
            Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
            if (requestArgsArr.length <= 1 && !isList(reqType)) {
                ui.serve("you're likely missing a request description");
                return unDoneCount;
            }
            switch (reqTypeEnum) {
            case LIST:
                if (tasks.isEmpty()) {
                    ui.serve("No new tasks, YAY", isPrintable);
                    ui.separator(isPrintable);
                    break;
                }
                ui.serve("That's a lot of things to do...", isPrintable);
                for (int i = 0; i < tasks.size(); i++) {
                    ui.serve(" " + (i + 1) + "." + tasks.get(i).toString(), isPrintable);
                }
                ui.separator(isPrintable);
                break;
            case FIND:
                String searchItem = reqArgsString.split(" ")[0];
                boolean found = false;
                ui.serve("Searching for your item....", isPrintable);
                for (Task taskItem : tasks) {
                    if (taskItem.getDescription().toLowerCase().contains(searchItem.toLowerCase())) {
                        ui.serve(taskItem.toString(), isPrintable);
                        found = true;
                    }
                }
                if (!found) {
                    ui.serve("no such item!!!");
                }
                ;
                break;
            case MARK:
                int index = parser.getIntToMark(reqArgsString);
                task = tasks.get(index - 1);
                if (task.isComplete()) {
                    ui.serve("Task already marked as completed", isPrintable);
                    break;
                } else {
                    task.setComplete(true);
                    unDoneCount--;
                    ui.serveRequestBack("Alhamdulillah one down", task, unDoneCount, isPrintable);
                }
                break;
            case UNMARK:
                index = parser.getIntToMark(reqArgsString);
                task = tasks.get(index - 1);
                if (!task.isComplete()) {
                    ui.serve("Task already marked as not completed", isPrintable);
                    break;
                } else {
                    task.setComplete(false);
                    unDoneCount++;
                    ui.serveRequestBack("Verily with hardship comes ease", task, unDoneCount, isPrintable);
                }
                break;
            case EVENT:
                task = new Event(reqArgsString.split("/from")[0], reqArgsString.split("/from")[1].split("/to")[0],
                        reqArgsString.split("/to")[1]);
                tasks.add(task);
                unDoneCount++;
                ui.serveRequestBack("Sure thing, I've added this task:", task, unDoneCount, isPrintable);
                break;
            case DEADLINE:
                task = new Deadline(reqArgsString.split("/by")[0], reqArgsString.split("/by")[1]);
                tasks.add(task);
                unDoneCount++;
                ui.serveRequestBack("Sure thing, I've added this task:", task, unDoneCount, isPrintable);
                break;
            case TODO:
                task = new Todo(reqArgsString);
                tasks.add(task);
                unDoneCount++;
                ui.serveRequestBack("Sure thing, I've added this task:", task, unDoneCount, isPrintable);
                break;
            case DELETE:
                index = Integer.parseInt(reqArgsString);
                task = tasks.get(index - 1);
                if (!task.isComplete()) {
                    unDoneCount--;
                }
                tasks.remove(index - 1);
                ui.serveRequestBack("OKIE DELETED THIS TASK:", task, unDoneCount, isPrintable);
                break;
            case GET:
                if (reqArgsString.equalsIgnoreCase("OVERDUE")) {
                    ui.serve("Here are the OVERDUE tasks :)", isPrintable);
                    tasks.stream().filter(overdueTask -> overdueTask instanceof Deadline)
                            .filter(overdueTask -> overdueTask.isOverdue() && !overdueTask.isComplete())
                            .forEach(overdueTask -> ui.serve("" + overdueTask.toString(), isPrintable));
                    ui.separator();
                }
                break;
            default:
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.serve("You've inputted an invalid description for this task,\n"
                    + "please consult the docs.\n");
            ui.separator();
        } catch (IndexOutOfBoundsException e) {
            ui.serve("Your item doesn't...exist....\n");
            ui.separator();
        } catch (DateTimeParseException e) {
            ui.serve("Your date format is wrong.\n     Please use the YYYY-MM-DD HH:mm OR MMM dd yyyy hh:mm a"
                    + " format\n");
            ui.separator();
        } catch (IllegalArgumentException e) {
            ui.separator();
            ui.serve("Please use one of the following commands:\n");
            ui.serve("list, mark, unmark, event, deadline, todo, get.\n");
            ui.separator();
        }
        return unDoneCount;
    }


}
