package abuhurairah;
// change to script class
/**
 * The Ui class handles all user interface interactions, including
 * displaying messages, errors, and task updates.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }
    /**
     * Displays the exit message.
     */
    public static String bye() {
        return "Hope to see you again soon!\n";
    }

    /**
     * Displays an error message for storage.
     *
     * @param e The exception that occurred.
     */
    public static String error(Exception e) {
        return "unable to save Data :(" + e.getMessage();
    }

    /**
     * Displays a response message along with a task and the number of remaining tasks.
     *
     * @param s           The response message.
     * @param task        The task being processed.
     * @param i           The number of remaining tasks.
     */
    public static String serveRequestBack(String s, Task task, int i) {
        return s + "\n" + task.toString()
                + "\n" + i + " tasks left love.\n";
    }

    /**
     * Displays the welcome back message.
     *
     * @param name The name of the bot or system.
     */
    public static String showWelcomeBack(String name) {
        return "Assalamualaikum! Welcome Back! I'm "
                + name + "\nWhat can I do for you?";
    }

    /**
     * Displays the initial welcome message.
     *
     * @param name The name of the bot or system.
     */
    public static String showWelcome(String name) {
        return "Assalamualaikum! I'm "
                + name + "\nWhat can I do for you?";
    }
}
