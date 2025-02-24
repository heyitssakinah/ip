package abuhurairah;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import abuhurairah.task.Event;

public class EventTest {
    @Test
    public void toString_validInput_correctFormat() {
        Event event = new Event("Project Meeting", "2024-02-10 10:00", "2024-02-10 12:00");
        assertEquals("[E][ ] Project Meeting(from:2024-02-10 10:00to:2024-02-10 12:00)", event.toString());
    }
}
