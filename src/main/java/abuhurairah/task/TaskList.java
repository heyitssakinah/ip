package abuhurairah.task;

import static abuhurairah.task.CommandType.bye;
import static abuhurairah.task.CommandType.list;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import abuhurairah.command.AddCommand;
import abuhurairah.command.AliasCommand;
import abuhurairah.command.DeleteCommand;
import abuhurairah.command.MarkCommand;
import abuhurairah.command.RetrieveCommand;
import abuhurairah.storage.Parser;



/**
 * The TaskList class manages a list of tasks.
 * It allows adding, retrieving, and modifying tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private TaskTracker taskTracker;
    private Parser parser;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.parser = new Parser();
        this.taskTracker = new TaskTracker();
    }

    /**
     * Generates a string representation of the list of tasks.
     *
     * @return A string representing the list of tasks,
     */
    public String printStoredList() {
        String storedList = "";
        for (int i = 0; i < tasks.size(); i++) {
            storedList += " " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return storedList + "\n";
    }

    /**
     * Checks if the given request is a "bye" command.
     *
     * @param request The input request.
     * @return true if the request is "bye", otherwise false.
     */
    public boolean isBye(String request) {
        return request.equalsIgnoreCase(bye.toString());
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
     * @return A TaskTracker object
     */
    public TaskTracker getTaskTracker() {
        return taskTracker;
    }


    /**
     * Checks if the given request is a "list" command.
     *
     * @param request The input request.
     * @return true if the request is "list", otherwise false.
     */
    public boolean isList(String request) {
        return request.equalsIgnoreCase(list.toString());
    }

    /**
     * Checks if the given request is a "list" command.
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
     * @return response of the bot
     */
    public String argumentHandling(String taskString) {
        assert tasks != null : "TaskList cannot be null";
        String[] requestArgsArr = taskString.split(" ");
        String reqType = requestArgsArr[0];
        try {
            CommandType command = CommandAlias.getCommandType(reqType.toLowerCase());
            if (command == null) {
                throw new CustomException("""
                        Please use one of the following commands:
                        list, mark, unmark, event, deadline, todo, get, find.
                        """);
            }
            if (requestArgsArr.length == 1 && !isList(reqType) && !isBye(reqType)) {
                throw new CustomException("you're likely missing a request description");
            }
            String reqArgsString = parser.getArgs(requestArgsArr);
            switch (command) {
            case list:
                return RetrieveCommand.listTasks(tasks);
            case find:
                return RetrieveCommand.findTask(reqArgsString, tasks);
            case mark:
                return MarkCommand.markTask(reqArgsString, tasks, taskTracker);
            case unmark:
                return MarkCommand.unMarkTask(reqArgsString, tasks, taskTracker);
            case event:
                return AddCommand.addEvent(reqArgsString, tasks, taskTracker);
            case deadline:
                return AddCommand.addDeadline(reqArgsString, tasks, taskTracker);
            case todo:
                return AddCommand.addTodo(reqArgsString, tasks, taskTracker);
            case delete:
                return DeleteCommand.deleteTask(reqArgsString, tasks, taskTracker);
            case get:
                return RetrieveCommand.getOverdueTask(reqArgsString, tasks);
            case alias:
                return AliasCommand.setAlias(reqArgsString);
            default:
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "You've inputted an invalid description for this task,\n please consult the docs.\n";
        } catch (IndexOutOfBoundsException e) {
            return "Your item doesn't...exist....\n";
        } catch (DateTimeParseException e) {
            return "Your date format is wrong.\n "
                    + "Please use the YYYY-MM-DD HH:mm OR MMM dd yyyy hh:mm a format\n";
        } catch (CustomException e) {
            return e.getMessage();
        }
        return "";
    }


}
