package ally.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start time and end time.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an Event task with the specified name, start time, and end time.
     *
     * @param taskName  the name of the task
     * @param startTime the start time of the event
     * @param endTime   the end time of the event
     */
    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the start time of the event.
     *
     * @return the start time of the event
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return the end time of the event
     */
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
