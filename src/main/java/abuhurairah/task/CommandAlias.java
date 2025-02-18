package abuhurairah.task;

import java.util.HashMap;
import java.util.Map;

public class CommandAlias {
    private static final Map<String, CommandType> aliasMap = new HashMap<>();

    static {
        aliasMap.put("todo", CommandType.todo);
        aliasMap.put("event", CommandType.event);
        aliasMap.put("deadline", CommandType.deadline);
        aliasMap.put("mark", CommandType.mark);
        aliasMap.put("unmark", CommandType.unmark);
        aliasMap.put("list", CommandType.list);
        aliasMap.put("delete", CommandType.delete);
        aliasMap.put("bye", CommandType.bye);
        aliasMap.put("get", CommandType.get);
        aliasMap.put("find", CommandType.find);
        aliasMap.put("alias", CommandType.alias);
    }

    public static void setAlias(String commandInput, String alias) {
        CommandType command = CommandType.valueOf(commandInput);
        aliasMap.put(alias, command);
    }

    public static CommandType getCommandType(String input) {
        return aliasMap.getOrDefault(input, null);
    }
}
