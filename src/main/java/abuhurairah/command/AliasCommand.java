package abuhurairah.command;

import abuhurairah.task.CommandAlias;
import abuhurairah.task.CommandType;

/**
 * Handles input alias
 */
public class AliasCommand {
    public static String setAlias(String reqArgsString) {
        String[] request;
        request = reqArgsString.split(" ");
        //get command type
        String command = request[0].toLowerCase();
        String alias = request[1];

        // Validate if the command exists in CommandType
        CommandType commandCheck;
        try {
            commandCheck = CommandAlias.getCommandType(command);
        } catch (IllegalArgumentException e) {
            return "Invalid command: " + command;
        }

        CommandAlias.setAlias(command, alias);
        return "Alias for " + command + " created: " + alias;
    }
}
