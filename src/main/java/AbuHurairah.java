import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class AbuHurairah {

  static String NAME = "Abu Hurairah";
  static String LINE =
    "    ____________________________________________________________";
  public static enum Req {
    MARK, UNMARK, EVENT, DEADLINE, TODO, LIST, BYE;
  }

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

    ArrayList<Task> list = new ArrayList<>();
    int unDoneCount = 0;
    // Get request
    Scanner input = new Scanner(System.in);
    String regex = "[[\\[X]\\]]";
    while (true) {
      String request = input.nextLine();
      if (request.equalsIgnoreCase(Req.BYE.toString())) {
        serve(LINE + "\n     Hope to see you again soon!\n" + LINE + "\n");
        break;
      } else {
        serve(LINE);
        Task task;
        String[] requestArgsArr = request.split(" ");
        String reqType = requestArgsArr[0].toUpperCase();
        String reqArgsString = requestArgsArr.length > 1
                ? String.join(" ", Arrays.asList(requestArgsArr).subList(1, requestArgsArr.length))
                : "\n";
        Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
        try {
          switch (reqTypeEnum) {
            case MARK:
              int index = Integer.parseInt(reqArgsString);
              task = list.get(index - 1);
              task.setComplete(true);
              unDoneCount--;
              serve("     Alhamdulillah one down");
              serve("       " + task.toString() + "\n" + "     " +
                      unDoneCount + " tasks left love.");
              serve(LINE + "\n");
              break;
            case UNMARK:
              index = Integer.parseInt(reqArgsString);
              task = list.get(index - 1);
              task.setComplete(false);
              unDoneCount++;
              serve("     Verily with hardship comes ease");
              serve("       " + task.toString() + "\n" + "     " +
                      unDoneCount + " tasks left love.");
              serve(LINE + "\n");
              break;
            case EVENT:
              task = new Event(reqArgsString.split("/from")[0], reqArgsString.split("/from")[1].split("/to")[0],
                      reqArgsString.split("/to")[1]);
              list.add(task);
              unDoneCount++;
              serve("     Sure thing, I've added this task:\n       " + task.toString() +
                      "\n     " + list.size() + " tasks left love.");
              serve(LINE + "\n");
              break;
            case DEADLINE:
              task = new Deadline(reqArgsString.split("/by")[0], reqArgsString.split("/by")[1]);
              list.add(task);
              unDoneCount++;
              serve("     Sure thing, I've added this task:\n       " + task.toString() +
                      "\n     " + list.size() + " tasks left love.");
              serve(LINE + "\n");
              break;
            case TODO:
              task = new Todo(reqArgsString);
              list.add(task);
              unDoneCount++;
              serve("     Sure thing, I've added this task:\n       " + task.toString() +
                      "\n     " + list.size() + " tasks left love.");
              serve(LINE + "\n");
              break;
            case LIST:
              serve("     That's a lot of things to do...");
              for (int i = 0; i < list.size(); i++) {
                serve("      " + (i + 1) + "." + list.get(i).toString());
              }
              serve(LINE + "\n");
              break;
            default:
          }
        } catch (IllegalArgumentException e){
          serve("please use one of the following commands:" +
                  "list, mark, unmark, event, deadline, todo");
        }
      }
    }
  }
}
