package abuhurairah;
/**
 * The main class for the AbuHurairah task management application.
 * This program stores and retrieves tasks, allowing users to interact
 * via a command-line interface.
 */
public class AbuHurairah {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    public String getResponse(String request, TaskList taskList) {
        String name = "Abu Hurairah";
        int unDoneCount = 0;
        // handle request
        return taskList.argumentHandling(request, unDoneCount);
    }
}
