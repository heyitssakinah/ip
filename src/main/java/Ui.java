public class Ui {
    private String LINE =
            "    ____________________________________________________________";

    public Ui() {
    }

    public void serve(String s, boolean bool) {
        if (bool) {
            System.out.println("     " + s);
        }
    }

    public void bye() {
        seperator();
        serve("Hope to see you again soon!\n");
        seperator();
    }

    public void error(Exception e) {
        seperator();
        serve("unable to save Data :(" + e.getMessage());
    }

    public void serve(String s) {
        System.out.println("     " + s);
    }

    public void serveRequestBack(String s, Task task, int i, boolean isPrintable) {
        serve(s + "\n       " + task.toString() +
                "\n     " + i + " tasks left love.\n" + LINE + "\n", isPrintable);
    }

    public void showWelcomeBack(String name) {
        seperator();
        serve("Assalamualaikum! Welcome Back! I'm " +
                name + "\n");
        serve("What can I do for you?");
        seperator();
    }

    public void showWelcome(String name) {
        seperator();
        serve("Assalamualaikum! I'm " +
                name + "\n");
        serve("What can I do for you?");
        seperator();
    }

    public void line() {
        System.out.println(LINE);
    }

    public void line(boolean b) {
        if (b) {
            System.out.println(LINE);
        }
    }

    public void seperator() {
        System.out.println(LINE + "\n");
    }

    public void tab() {
        System.out.println("     ");
    }

    public void seperator(boolean b) {
        if (b) {
            System.out.println(LINE + "\n");
        }
    }


}