package ally.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ally.allyexception.AllyException;
import ally.allyexception.EmptyTaskNameException;
import ally.allyexception.InvalidCommandFormatException;
import ally.allyexception.InvalidDateTimeException;
import ally.parser.Parser;
import ally.tasklist.Event;
import ally.tasklist.Task;
import ally.tasklist.TaskList;
import ally.ui.UI;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private String response = "Command executed";

    /**
     * Constructs an AddEventCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the input format is invalid or the task name is empty.
     */
    public AddEventCommand(String input) throws AllyException {
        String[] parts = input.split(" /from ");
        if (parts.length != 2) {
            throw new InvalidCommandFormatException("event", "Missing '/from' time");
        }

        this.description = parts[0].substring(5).trim();
        if (description.isEmpty()) {
            throw new EmptyTaskNameException("event");
        }

        String[] time = parts[1].split(" /to ");
        if (time.length != 2) {
            throw new InvalidCommandFormatException("event", "Missing '/to' time");
        }

        try {
            this.startTime = Parser.parseDateTime(time[0].trim());
            String endTimeStr = time[1].trim();
            if (Parser.isTimeOnly(endTimeStr)) {
                String startDate = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.endTime = Parser.parseDateTime(startDate + " " + endTimeStr);
            } else {
                this.endTime = Parser.parseDateTime(endTimeStr);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event");
        }
    }

    /**
     * Executes the command to add an event task.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        Task task = new Event(description, startTime, endTime);
        tasks.add(task);
        response = "Added event: " + task.toString();
        ui.showTaskAdded(task, tasks.size());
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
