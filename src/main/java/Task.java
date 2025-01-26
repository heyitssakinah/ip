public abstract class Task {
    private String description;
    private boolean complete;

    public Task(String description) {
        this.description = description;
        this.complete = false;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String toString() {
        return complete ? "[X] " + description : "[ ] " + description;
    }
}