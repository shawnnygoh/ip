package ally.command;

import java.time.*;
import java.time.format.*;

import ally.allyexception.EmptyTaskNameException;
import ally.allyexception.*;
import ally.parser.*;
import ally.tasklist.*;
import ally.ui.*;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

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

    @Override
    public void execute(TaskList tasks, UI ui) {
        Task task = new Event(description, startTime, endTime);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }
}
