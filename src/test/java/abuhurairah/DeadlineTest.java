package abuhurairah;

import abuhurairah.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineTest {

    @Test
    public void testDeadlineConstructorWithValidDateFormats() {
        // Testing first format (e.g., "Jan 01 2025 10:30 AM")
        Deadline deadline1 = new Deadline("Complete project", "Jan 01 2025 10:30 am");
        assertNotNull(deadline1);  // Ensure object is created
        assertEquals("[D][ ] Complete project (by: Jan 01 2025 10:30 am)", deadline1.toString()); // Check task

        // Testing second format (e.g., "2025-01-01 10:30")
        Deadline deadline2 = new Deadline("Submit report", "2025-01-01 10:30");
        assertNotNull(deadline2); // Ensure object is created
        assertEquals("[D][ ] Submit report (by: Jan 01 2025 10:30 am)", deadline2.toString()); // Check task
    }

    @Test
    public void testIsOverdueBeforeDueDate() {
        // Creating a future deadline
        Deadline deadline = new Deadline("Finish homework", "Dec 31 2025 10:30 pm");

        // Ensuring it's not overdue at the moment
        assertFalse(deadline.isOverdue());
    }

    @Test
    public void testIsOverdueAfterDueDate() {
        // Creating a past deadline
        Deadline deadline = new Deadline("Finish homework", "Jan 01 2020 10:30 am");

        // Ensuring it's overdue
        assertTrue(deadline.isOverdue());
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        // Expecting an exception due to an invalid date format
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Test task", "invalid date format");
        });
    }
}