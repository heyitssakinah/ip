package abuhurairah;

public abstract class Task {
    private String description;
    private boolean complete;

    public Task(String description) {
        this.description = description;
        this.complete = false;
    }

    public String getDescription() {
        return description;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public boolean isOverdue() {
        return false;
    }

    public String toString() {
        return complete ? "[X] " + description : "[ ] " + description;
    }
}