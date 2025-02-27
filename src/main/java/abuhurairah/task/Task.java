package abuhurairah.task;

/**
 * Represents an abstract task with a description and completion status.
 * This class serves as a base for different types of tasks, such as
 * deadlines and events.
 */
public abstract class Task {
    private final String description;
    private boolean isComplete;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as incomplete.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Gets the description of a task
     *
     * @return A string of the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param complete true if the task is completed, false otherwise.
     */
    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, otherwise false .
     */
    public boolean isComplete() {
        return isComplete;
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
     * If the task is isComplete, it is marked with "[X]". Otherwise, it is marked with "[ ]".
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return isComplete ? "[X] " + description : "[ ] " + description;
    }

}
