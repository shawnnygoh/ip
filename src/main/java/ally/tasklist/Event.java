package ally.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        int hour = dateTime.getHour();
        if (hour == 0) {
            hour = 12;
        } else if (hour > 12) {
            hour -= 12;
        }

        String minutes;
        if (dateTime.getMinute() == 0) {
            minutes = "";
        } else {
            minutes = ":" + String.format("%02d", dateTime.getMinute());
        }

        String meridiemIndicator;
        if (dateTime.getHour() < 12) {
            meridiemIndicator = "am";
        } else {
            meridiemIndicator = "pm";
        }

        return formattedDateTime + " " + hour + minutes + meridiemIndicator;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(startTime) + " to: " + formatDateTime(endTime) + ")";
    }
}
