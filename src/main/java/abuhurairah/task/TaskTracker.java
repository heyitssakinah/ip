package abuhurairah.task;

/**
 * Keeps track of the number of completed tasks
 */
public class TaskTracker {
    private int completedTasks;

    public TaskTracker() {
        this.completedTasks = 0;
    }

    /**
     * Increments the count of completed tasks by one.
     * This method is called when a task is marked as completed.
     */
    public void addCompletedTask() {
        this.completedTasks += 1;
    }

    /**
     * Decrements the count of completed tasks by one.
     * This method is called when a completed task is marked as not completed.
     *
     * @throws AssertionError if the count of completed tasks becomes negative
     */
    public void removeCompletedTask() {
        completedTasks--;
        assert completedTasks >= 0 : "Completed tasks cannot be negative";
    }

    /**
     * Gets the current number of completed tasks.
     *
     * @return The number of completed tasks
     */
    public int getCompletedTasks() {
        return completedTasks;
    }

    /**
     * Calculates the number of remaining tasks based on the total number of tasks and the number of completed tasks.
     *
     * @param totalTasks The total number of tasks in the system
     * @return The number of remaining tasks
     */
    public int getRemainingTasks(int totalTasks) {
        return totalTasks - completedTasks;
    }
}
