package abuhurairah;

/**
 * Represents an event task with a start and end time.
 * This class extends the {@code Task} class and adds time-based details
 * to tasks that have a specific duration.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string representing the event, including its type,
     *         description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start + "to:" + this.end + ")";
    }
}
