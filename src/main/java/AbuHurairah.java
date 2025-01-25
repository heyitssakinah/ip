import java.util.Scanner;

public class AbuHurairah {
    static String NAME = "Abu Hurairah";
    static String LINE = "    ____________________________________________________________";

    public static void serve(String s) { System.out.println(s); }

    public static void main(String[] args) {
        // Welcome prompt
        serve(LINE + "\n" + "     Assalamualaikum! I'm " + NAME
                + "\n" + "     What can I do for you?\n" + LINE + "\n");
        Scanner input = new Scanner(System.in);
        while (true) {
            String request = input.nextLine();
            if (request.equals("bye")) {
                serve(LINE + "\n     Hope to see you again soon!\n" + LINE);
                break;
            }
            serve(LINE + "\n     " + request + "\n" + LINE + "\n");
        }
    }
}
