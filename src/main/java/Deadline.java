import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    private boolean overdue;

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

    @Override
    public boolean isOverdue() {
        if (LocalDateTime.now().isAfter(this.by)) {
            this.overdue = true;
        }
        return this.overdue;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) +
                ")";

    }
}