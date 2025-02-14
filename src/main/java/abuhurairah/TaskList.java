package abuhurairah;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It allows adding, retrieving, and modifying tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Parser parser;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.parser = new Parser();
    }

    public String printStoredList() {
        String storedList = "";
        for (int i = 0; i < tasks.size(); i++) {
            storedList += " " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return storedList;
    }

    /**
     * Enumeration of valid request types.
     */
    private enum Req {
        MARK, UNMARK, EVENT, DEADLINE, TODO, LIST, DELETE, BYE, GET, FIND
    }

    /**
     * Checks if the given request is a "bye" command.
     *
     * @param request The input request.
     * @return true if the request is "bye", otherwise false.
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
     * @return true if the request is "list", otherwise false.
     */
    boolean isList(String request) {
        return request.equalsIgnoreCase(Req.LIST.toString());
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, otherwise false.
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
     * @return response of the bot
     */
    public String argumentHandling(String taskString, int unDoneCount) {
        String[] requestArgsArr = taskString.split(" ");
        String reqType = requestArgsArr[0];
        String reqArgsString = parser.getArgs(requestArgsArr);
        try {
            Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
            if (requestArgsArr.length == 1 && !isList(reqType) && !isBye(reqType)) {
                return "you're likely missing a request description";
            }
            switch (reqTypeEnum) {
            case LIST:
                return RetrieveCommand.listTasks(tasks);
            case FIND:
                return RetrieveCommand.findTask(reqArgsString, tasks);
            case MARK:
                return MarkCommand.markTask(reqArgsString, tasks);
            case UNMARK:
                return MarkCommand.unMarkTask(reqArgsString, tasks);
            case EVENT:
                return AddCommand.addEvent(reqArgsString, tasks);
            case DEADLINE:
                return AddCommand.addDeadline(reqArgsString, tasks);
            case TODO:
                return AddCommand.addTodo(reqArgsString, tasks);
            case DELETE:
                return DeleteCommand.deleteTask(reqArgsString, tasks);
            case GET:
                return RetrieveCommand.getOverdueTask(reqArgsString, tasks);
            default:
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "You've inputted an invalid description for this task,\n please consult the docs.\n";
        } catch (IndexOutOfBoundsException e) {
            return "Your item doesn't...exist....\n";
        } catch (DateTimeParseException e) {
            return "Your date format is wrong.\n "
                    + "Please use the YYYY-MM-DD HH:mm OR MMM dd yyyy hh:mm a format\n";
        } catch (IllegalArgumentException e) {
            return "Please use one of the following commands:\n"
                    + "list, mark, unmark, event, deadline, todo, get.\n";
        }
        return "";
    }


}
