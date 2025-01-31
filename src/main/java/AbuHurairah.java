import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AbuHurairah {

    static String NAME = "Abu Hurairah";
    static String LINE =
            "    ____________________________________________________________";

    public static enum Req {
        MARK, UNMARK, EVENT, DEADLINE, TODO, LIST, DELETE, BYE;
    }

    public static void serve(String s, boolean bool) {

        if (bool) {
            System.out.println(s);
        }
    }

    public static void serveRequestBack(String s, Task task, int i, boolean isPrintable) {
        serve(s + "\n       " + task.toString() +
                "\n     " + i + " tasks left lovee.\n" + LINE + "\n", isPrintable);
    }

    /**
     * Writes all tasks in the provided list to a text file named "AbuHurairah.txt".
     * Each task is written on a new line in the file.
     *
     * @param list the list of tasks to be written to the file
     * @throws IOException if an I/O error occurs during file writing
     */
    public static void store(ArrayList<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter("./AbuHurairahHistory.txt");
        for (Task task : list) {
            fileWriter.write("     " + task.toString() + "\n");
        }
        fileWriter.close();
    }

    // Will move to an customExceptionFile later
    public static class MissingDescriptionException extends Exception {
        public MissingDescriptionException() {
            super("you're likely missing a request description");
        }
    }

    public static void printListHistory(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        serve(LINE, true);
        while (sc.hasNext()) {
            serve(sc.nextLine(), true);
        }
    }

    public static int updateCurrList(String path, ArrayList<Task> list) throws
            FileNotFoundException, IOException {
        int unDoneCount = 0;
        int taskCount = 1;
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            unDoneCount = textToArrayList(sc.nextLine(), list, unDoneCount, taskCount);
        }
        return unDoneCount;
    }

    public static int textToArrayList(String s, ArrayList<Task> list, int unDoneCount, int taskCount) {
        String cleanedString = s.replaceAll("\\[|\\(|\\)", "").trim();
        cleanedString = cleanedString.replaceAll("\\]", " ").trim();
        cleanedString = cleanedString.replaceAll("from:", "/from");
        cleanedString = cleanedString.replaceAll("to:", "/to");
        cleanedString = cleanedString.replaceAll("by:", "/by");
        cleanedString = cleanedString.replaceAll("E", "Event");
        cleanedString = cleanedString.replaceAll("D", "Deadline");
        cleanedString = cleanedString.replaceAll("T", "Todo");
        if (cleanedString.split(" ")[1].equals("X")) {
            cleanedString = cleanedString.replaceAll("X ", "");
            cleanedString = cleanedString.replaceAll("  ", " ");
            String[] requestArgsArr = cleanedString.split(" ");
            String reqType = requestArgsArr[0].toUpperCase();
            String reqArgsString = String.join(" ", Arrays.asList(requestArgsArr).
                    subList(1, requestArgsArr.length));
            Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
            unDoneCount = argumentHandling(reqTypeEnum, reqArgsString, list, unDoneCount, false);
            taskCount++;
            unDoneCount = argumentHandling(Req.valueOf("MARK"), String.valueOf(taskCount), list,
                    unDoneCount, false);
        } else {
            cleanedString = cleanedString.replaceAll("    ", " ");
            String[] requestArgsArr = cleanedString.split(" ");
            String reqType = requestArgsArr[0].toUpperCase();
            String reqArgsString = String.join(" ", Arrays.asList(requestArgsArr).
                    subList(1, requestArgsArr.length));
            Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
            unDoneCount = argumentHandling(reqTypeEnum, reqArgsString, list, unDoneCount, false);
            taskCount++;
        }
        return unDoneCount;
    }

    public static int argumentHandling(Req reqTypeEnum, String reqArgsString,
                                       ArrayList<Task> list, int unDoneCount, boolean isPrintable) {
        serve(LINE, isPrintable);
        Task task;
        try {
            switch (reqTypeEnum) {
                case LIST:
                    if (list.isEmpty()) {
                        serve("     No new tasks, YAY", isPrintable);
                        serve(LINE + "\n", isPrintable);
                        break;
                    }
                    serve("     That's a lot of things to do...", isPrintable);
                    for (int i = 0; i < list.size(); i++) {
                        serve("      " + (i + 1) + "." + list.get(i).toString(), isPrintable);
                    }
                    serve(LINE + "\n", isPrintable);
                    break;
                case MARK:
                    int index = Integer.parseInt(reqArgsString);
                    task = list.get(index - 1);
                    if (task.isComplete()) {

                        serve("     Task already marked as completed", isPrintable);
                        break;
                    } else {
                        task.setComplete(true);
                        unDoneCount--;
                        serveRequestBack("     Alhamdulillah one down", task, unDoneCount, isPrintable);
                    }
                    break;
                case UNMARK:
                    index = Integer.parseInt(reqArgsString);
                    task = list.get(index - 1);
                    if (!task.isComplete()) {
                        serve("Task already marked as not completed", isPrintable);
                        break;
                    } else {
                        task.setComplete(false);
                        unDoneCount++;
                        serveRequestBack("     Verily with hardship comes ease", task, unDoneCount, isPrintable);
                    }
                    break;
                case EVENT:
                    task = new Event(reqArgsString.split("/from")[0], reqArgsString.split("/from")[1].split("/to")[0],
                            reqArgsString.split("/to")[1]);
                    list.add(task);
                    unDoneCount++;
                    serveRequestBack("     Sure thing, I've added this task:", task, unDoneCount, isPrintable);
                    break;
                case DEADLINE:
                    task = new Deadline(reqArgsString.split("/by")[0], reqArgsString.split("/by")[1]);
                    list.add(task);
                    unDoneCount++;
                    serveRequestBack("     Sure thing, I've added this task:", task, unDoneCount, isPrintable);
                    break;
                case TODO:
                    task = new Todo(reqArgsString);
                    list.add(task);
                    unDoneCount++;
                    serveRequestBack("     Sure thing, I've added this task:", task, unDoneCount, isPrintable);
                    break;
                case DELETE:
                    index = Integer.parseInt(reqArgsString);
                    task = list.get(index - 1);
                    if (!task.isComplete()) {
                        unDoneCount--;
                    }
                    list.remove(index - 1);
                    serveRequestBack("     OKIE DELETED THIS TASK:", task, unDoneCount, isPrintable);
                default:
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            serve("     You've inputted an invalid description for this task,\n" +
                    "     please consult the docs.\n" + LINE + "\n", true);
        } catch (IndexOutOfBoundsException e) {
            serve("     Your item doesn't...exist....\n" + LINE + "\n", true);
        }
        return unDoneCount;
    }

    public static void main(String[] args) {
        int unDoneCount = 0;
        ArrayList<Task> list = new ArrayList<>();
        try {
            printListHistory("./AbuHurairahHistory.txt");
            unDoneCount = updateCurrList("./AbuHurairahHistory.txt", list);
            serve(LINE + "\n" + "     Assalamualaikum! Welcome Back! I'm " +
                    NAME + "\n" + "     What can I do for you?\n" + LINE + "\n", true);
        } catch (FileNotFoundException e) { // Start a new chat Bot
            serve(LINE + "\n" + "     Assalamualaikum! I'm " +
                    NAME + "\n" + "     What can I do for you?\n" + LINE + "\n", true);
        } catch (IOException e) {
            serve("Your previous life might be corrupted.\n No history found", true);
        }
        // Get request
        Scanner input = new Scanner(System.in);
        while (true) {
            if (!input.hasNextLine()) {
                break;
            }
            String request = input.nextLine();
            if (request.equalsIgnoreCase(Req.BYE.toString())) {
                if (!list.isEmpty()) {
                    try {
                        store(list);
                    } catch (IOException e) {
                        serve("unable to save Data :(" + e.getMessage(), true);
                    }
                }
                serve(LINE + "\n     Hope to see you again soon!\n" + LINE + "\n", true);
                break;
            }
            try {
                String[] requestArgsArr = request.split(" ");
                String reqType = requestArgsArr[0].toUpperCase();
                String reqArgsString = String.join(" ", Arrays.asList(requestArgsArr).
                        subList(1, requestArgsArr.length));
                Req reqTypeEnum = Req.valueOf(reqType.toUpperCase());
                if (requestArgsArr.length <= 1 && !reqType.equalsIgnoreCase(Req.LIST.toString())) {
                    throw new MissingDescriptionException();
                }
                unDoneCount = argumentHandling(reqTypeEnum, reqArgsString, list, unDoneCount, true);
            } catch (MissingDescriptionException e) {
                serve(LINE + "\n", true);
                serve("     You're missing a request description.\n" + LINE + "\n", true);
            } catch (IllegalArgumentException e) {
                serve(LINE + "\n", true);
                serve("     Please use one of the following commands:\n" +
                        "     list, mark, unmark, event, deadline, todo.\n" + LINE + "\n", true);
            }
        }
    }
}
