import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class AbuHurairah {

  static String NAME = "Abu Hurairah";
  static String LINE =
    "    ____________________________________________________________";
  public static enum Req {
    MARK, UNMARK, EVENT, DEADLINE, TODO, LIST, DELETE, BYE;
  }

  public static void serve(String s) {
    System.out.println(s);
  }

  public static void serveRequestBack(String s, Task task, int i) {
    serve(s + "\n       " + task.toString() +
            "\n     " + i + " tasks left love.\n" + LINE + "\n");
  }

  public static class MissingDescriptionException extends Exception {
    public MissingDescriptionException() {
      super("you're likely missing a request description");
    }
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
        try {
          String[] requestArgsArr = request.split(" ");
          String reqType = requestArgsArr[0].toUpperCase();
          String reqArgsString = String.join(" ", Arrays.asList(requestArgsArr).
                  subList(1, requestArgsArr.length));
          if (requestArgsArr.length <= 1 && !reqType.equalsIgnoreCase(Req.LIST.toString())) {
            throw new MissingDescriptionException();
          }
          Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
          switch (reqTypeEnum) {
            case LIST:
              if(list.isEmpty()) {
                serve("     No new tasks, YAY");
                serve(LINE + "\n");
                break;
              }
              serve("     That's a lot of things to do...");
              for (int i = 0; i < list.size(); i++) {
                serve("      " + (i + 1) + "." + list.get(i).toString());
              }
              serve(LINE + "\n");
              break;
            case MARK:
              int index = Integer.parseInt(reqArgsString);
              task = list.get(index - 1);
              if (task.isComplete()) {
                serve("Task already marked as completed");
                break;
              } else {
                task.setComplete(true);
                unDoneCount--;
                serveRequestBack("     Alhamdulillah one down", task, unDoneCount);
              }
              break;
            case UNMARK:
              index = Integer.parseInt(reqArgsString);
              task = list.get(index - 1);
              if (!task.isComplete()) {
                serve("Task already marked as not completed");
                 break;
              } else {
                task.setComplete(false);
                unDoneCount++;
                serveRequestBack("     Verily with hardship comes ease", task, unDoneCount);
              }
              break;
            case EVENT:
              task = new Event(reqArgsString.split("/from")[0], reqArgsString.split("/from")[1].split("/to")[0],
                      reqArgsString.split("/to")[1]);
              list.add(task);
              unDoneCount++;
              serveRequestBack("     Sure thing, I've added this task:", task, unDoneCount);
              break;
            case DEADLINE:
              task = new Deadline(reqArgsString.split("/by")[0], reqArgsString.split("/by")[1]);
              list.add(task);
              unDoneCount++;
              serveRequestBack("     Sure thing, I've added this task:", task, unDoneCount);
              break;
            case TODO:
              task = new Todo(reqArgsString);
              list.add(task);
              unDoneCount++;
              serveRequestBack("     Sure thing, I've added this task:", task, unDoneCount);
              break;
            case DELETE:
              index = Integer.parseInt(reqArgsString);
              task = list.get(index - 1);
              if (!task.isComplete()) {
                unDoneCount--;
              }
              list.remove(index - 1);
              serveRequestBack("     OKIE DELETED THIS TASK:", task, unDoneCount);
            default:
          }
        } catch (IllegalArgumentException e) {
          serve("     Please use one of the following commands:\n" +
                  "     list, mark, unmark, event, deadline, todo.\n" + LINE + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
          serve("     You've inputted an invalid description for this task,\n" +
                  "     please consult the docs.\n" + LINE + "\n");
        } catch (MissingDescriptionException e) {
          serve("     You're missing a request description.\n" + LINE + "\n");
        } catch (IndexOutOfBoundsException e) {
          serve("     Your item doesn't...exist....\n" + LINE + "\n");
        }
      }
    }
  }
}
