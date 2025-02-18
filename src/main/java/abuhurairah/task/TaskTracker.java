package abuhurairah.task;

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
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public int getRemainingTasks(int totalTasks) {
        return totalTasks - completedTasks;
    }
}
