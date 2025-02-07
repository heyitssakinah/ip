package abuhurairah;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * This class extends the Task class and includes a due date,
 * which can be formatted in different ways.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private boolean overdue;

    /**
     * Constructs a Deadline task with a description and a due date.
     * The due date can be formatted either as "MMM dd yyyy hh:mm a" (e.g., "Jan 01 2025 10:30 AM")
     * or as "yyyy-MM-dd HH:mm" (e.g., "2025-01-01 10:30").
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time of the task, in one of the accepted formats.
     * @throws DateTimeParseException If the provided date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        if (by.toLowerCase().contains("am") || by.toLowerCase().contains("pm")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            this.by = LocalDateTime.parse(by.trim(), formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.by = LocalDateTime.parse(by.trim(), formatter);
        }
        this.overdue = false;
    }

    /**
     * Checks whether the task is overdue.
     *
     * @return {@code true} if the current date and time is after the due date, otherwise {@code false}.
     */
    @Override
    public boolean isOverdue() {
        if (LocalDateTime.now().isAfter(this.by)) {
            this.overdue = true;
        }
        return this.overdue;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string representing the deadline task, including its type,
     * description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) +
                ")";

    }
}