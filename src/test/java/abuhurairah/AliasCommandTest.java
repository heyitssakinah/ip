package abuhurairah;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import abuhurairah.command.AliasCommand;

public class AliasCommandTest {
    @Test
    public void argumentHandling_createsValidAlias() {
        String s = "todo t";
        String res = AliasCommand.setAlias(s);
        assertEquals("Alias for todo created: t", res);
    }
}
