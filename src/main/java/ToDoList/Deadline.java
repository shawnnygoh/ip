package ToDoList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

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
