package abuhurairah;

/**
 * Represents an abstract task with a description and completion status.
 * This class serves as a base for different types of tasks, such as
 * deadlines and events.
 */
public abstract class Task {
    private final String description;
    private boolean complete;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as incomplete.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.complete = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param complete {@code true} if the task is completed, {@code false} otherwise.
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, otherwise false .
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Determines whether the task is overdue.
     * This method is meant to be overridden by subclasses that have time constraints.
     *
     * @return false by default, unless overridden in a subclass.
     */
    public boolean isOverdue() {
        return false;
    }

    /**
     * Returns a string representation of the task.
     * If the task is complete, it is marked with "[X]". Otherwise, it is marked with "[ ]".
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return complete ? "[X] " + description : "[ ] " + description;
    }
}