package abuhurairah.task;

/**
 * Represents a simple to-do task without any time constraints.
 * A Todo task only has a description and a completion status.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     * The format includes a "[T]" marker to indicate the task type.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
