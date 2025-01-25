import java.util.ArrayList;
import java.util.Scanner;

public class AbuHurairah {

  static String NAME = "Abu Hurairah";
  static String LINE =
    "    ____________________________________________________________";

  public static void serve(String s) {
    System.out.println(s);
  }

  public static void main(String[] args) {
    // Welcome prompt
    serve(
      LINE +
      "\n" +
      "     Assalamualaikum! I'm " +
      NAME +
      "\n" +
      "     What can I do for you?\n" +
      LINE +
      "\n"
    );

    ArrayList<String> list = new ArrayList<>();
    // Get request
    Scanner input = new Scanner(System.in);
    String regex = "[[\\[X]\\]]";
    while (true) {
      String request = input.nextLine();
      if (request.equals("bye")) {
        serve(LINE + "\n     Hope to see you again soon!\n" + LINE);
        break;
      } else if (request.split(" ")[0].equals("mark")) {
        serve(LINE);
        int index = Integer.parseInt(request.split(" ")[1]);
        String[] item = list.get(index - 1).split(regex);
        list.set(index - 1, "[X]" + item[item.length - 1]);
        serve("     Alhamdulillah one down");
        serve("       " + list.get(index - 1) + "\n" + LINE + "\n");
      } else if (request.split(" ")[0].equals("unmark")) {
        serve(LINE);
        int index = Integer.parseInt(request.split(" ")[1]);
        String[] item = list.get(index - 1).split(regex);
        list.set(index - 1, "[ ]" + item[item.length - 1]);
        serve("     Verily with hardship comes ease");
        serve("       " + list.get(index - 1) + "\n" + LINE + "\n");
      } else if (request.equals("list")) {
        serve(LINE);
        serve("     That's a lot of things to do...");
        for (int i = 0; i < list.size(); i++) {
          serve("      " + (i + 1) + "." + list.get(i));
        }
        serve(LINE + "\n");
      } else {
        list.add("[ ] " + request);
        serve(LINE + "\n     " + "added: " + request + "\n" + LINE + "\n");
      }
    }
  }
}
