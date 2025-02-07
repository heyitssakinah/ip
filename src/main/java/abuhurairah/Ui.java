package abuhurairah;

/**
 * The Ui class handles all user interface interactions, including
 * displaying messages, errors, and task updates.
 */
public class Ui {
    private String LINE =
            "    ____________________________________________________________";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints a message if the specified condition is true.
     *
     * @param s    The message to print.
     * @param bool If true, the message is printed.
     */
    public void serve(String s, boolean bool) {
        if (bool) {
            System.out.println("     " + s);
        }
    }

    /**
     * Displays the exit message.
     */
    public void bye() {
        separator();
        serve("Hope to see you again soon!\n");
        separator();
    }

    /**
     * Displays an error message for storage.
     *
     * @param e The exception that occurred.
     */
    public void error(Exception e) {
        separator();
        serve("unable to save Data :(" + e.getMessage());
    }

    /**
     * Prints a message.
     *
     * @param s The message to print.
     */
    public void serve(String s) {
        System.out.println("     " + s);
    }

    /**
     * Displays a response message along with a task and the number of remaining tasks.
     *
     * @param s           The response message.
     * @param task        The task being processed.
     * @param i           The number of remaining tasks.
     * @param isPrintable If true, the message is printed.
     */
    public void serveRequestBack(String s, Task task, int i, boolean isPrintable) {
        serve(s + "\n       " + task.toString() +
                "\n     " + i + " tasks left love.\n" + LINE + "\n", isPrintable);
    }

    /**
     * Displays the welcome back message.
     *
     * @param name The name of the bot or system.
     */
    public void showWelcomeBack(String name) {
        separator();
        serve("Assalamualaikum! Welcome Back! I'm " +
                name + "\n");
        serve("What can I do for you?");
        separator();
    }

    /**
     * Displays the initial welcome message.
     *
     * @param name The name of the bot or system.
     */
    public void showWelcome(String name) {
        separator();
        serve("Assalamualaikum! I'm " +
                name + "\n");
        serve("What can I do for you?");
        separator();
    }

    /**
     * Prints a separator line.
     */
    public void line() {
        System.out.println(LINE);
    }

    /**
     * Prints a separator line if the specified condition is true.
     *
     * @param b If true, the line is printed.
     */
    public void line(boolean b) {
        if (b) {
            System.out.println(LINE);
        }
    }

    /**
     * Prints a separator line followed by a newline.
     */
    public void separator() {
        System.out.println(LINE + "\n");
    }

    /**
     * Prints a separator line followed by a newline if the specified condition is true.
     *
     * @param b If true, the separator is printed.
     */
    public void separator(boolean b) {
        if (b) {
            System.out.println(LINE + "\n");
        }
    }


}