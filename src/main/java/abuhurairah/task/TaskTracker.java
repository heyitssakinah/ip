package abuhurairah.task;

/**
 * Keeps track of the number of completed tasks
 */
public class TaskTracker {
    private int completedTasks;

    public TaskTracker() {
        this.completedTasks = 0;
    }

    public void addCompletedTask() {
        completedTasks++;
    }

    public void removeCompletedTask() {
        completedTasks--;
        assert completedTasks >= 0 : "Completed tasks cannot be negative";
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public int getRemainingTasks(int totalTasks) {
        return totalTasks - completedTasks;
    }
}
