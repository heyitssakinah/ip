package abuhurairah;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import abuhurairah.command.AliasCommand;
import abuhurairah.task.CommandAlias;
import abuhurairah.task.CommandType;

public class CommandAliasTest {
    @Test
    public void setAlias_createsValidAlias() {
        String s = "todo t";
        String res = AliasCommand.setAlias(s);
        CommandType command = CommandAlias.getCommandType("t");
        assertTrue(command.equals(CommandType.todo));
    }
}
