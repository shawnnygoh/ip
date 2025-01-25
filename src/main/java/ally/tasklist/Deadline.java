package ally.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified name and deadline.
     *
     * @param taskName the name of the task
     * @param deadline the due date and time of the task
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return the deadline of the task
     */
    public LocalDateTime getDate() {
        return deadline;
    }

    private String formatDateTime() {
        String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        int hour = deadline.getHour();
        if (hour == 0) {
            hour = 12;
        } else if (hour > 12) {
            hour -= 12;
        }

        String minutes;
        if (deadline.getMinute() == 0) {
            minutes = "";
        } else {
            minutes = ":" + String.format("%02d", deadline.getMinute());
        }

        String meridiemIndicator;
        if (deadline.getHour() < 12) {
            meridiemIndicator = "am";
        } else {
            meridiemIndicator = "pm";
        }

        return formattedDeadline + " " + hour + minutes + meridiemIndicator;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime() + ")";
    }
}
