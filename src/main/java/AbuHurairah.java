import java.util.Scanner;
import java.util.ArrayList;

public class AbuHurairah {
    static String NAME = "Abu Hurairah";
    static String LINE = "    ____________________________________________________________";

    public static void serve(String s) { System.out.println(s); }

    public static void main(String[] args) {
        // Welcome prompt
        serve(LINE + "\n" + "     Assalamualaikum! I'm " + NAME
                + "\n" + "     What can I do for you?\n" + LINE + "\n");

        ArrayList<String> list = new ArrayList<>();
        // Get request
        Scanner input = new Scanner(System.in);
        while (true) {
            String request = input.nextLine();
            if (request.equals("bye")) {
                serve(LINE + "\n     Hope to see you again soon!\n" + LINE);
                break;
            } else if (request.equals("list")) {
                serve(LINE);
                for (int i = 0; i < list.size(); i++) {
                    serve("     " + (i + 1) + ". " + list.get(i));
                }
                serve(LINE + "\n");
            } else {
                list.add(request);
                serve(LINE + "\n     " + "added: " + request + "\n" + LINE + "\n");
            }
        }
    }
}
