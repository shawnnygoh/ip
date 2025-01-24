package ToDoList;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTime() {
        String start = startTime;
        String end = endTime;

        String startPeriod = "";
        String endPeriod = "";

        if (start.toLowerCase().contains("am")) {
            startPeriod = "am";
        }

        if (start.toLowerCase().contains("pm")) {
            startPeriod = "pm";
        }

        if (end.toLowerCase().contains("am")) {
            endPeriod = "am";
        }

        if (end.toLowerCase().contains("pm")) {
            endPeriod = "pm";
        }

        start = start.replaceAll("(?i)am|pm", "").trim();
        end = end.replaceAll("(?i)am|pm", "").trim();

        return start + startPeriod + "-" + end + endPeriod;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
