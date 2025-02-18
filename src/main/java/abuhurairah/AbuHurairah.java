package abuhurairah;

import abuhurairah.storage.Storage;
import abuhurairah.task.TaskList;

/**
 * The main class for the AbuHurairah task management application.
 * This program stores and retrieves tasks, allowing users to interact
 * via a command-line interface.
 */
public class AbuHurairah {
    private final String name = "Abu Hurairah";
    private final Storage storage = new Storage("./AbuHurairahHistory.txt");
    private TaskList taskList = new TaskList();

    public String getName() {
        return name;
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getResponse(String request, TaskList taskList) {
        // handle request
        return taskList.argumentHandling(request);
    }
}
