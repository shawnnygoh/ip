package ally.command;

import java.time.*;
import java.time.format.*;

import ally.parser.*;
import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

public class FindCommand extends Command {
    private final LocalDateTime searchDate;

    public FindCommand(String input) throws AllyException {
        try {
            String dateStr = input.substring(5).trim();
            this.searchDate = Parser.parseDateTime(dateStr);
        } catch (DateTimeParseException e) {
            throw new AllyException("Sorry, the date format is invalid. 😵");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui) {
        LocalDateTime dayStart = searchDate.withHour(0).withMinute(0);
        LocalDateTime dayEnd = searchDate.withHour(23).withMinute(59);
        ui.showTasksOnDate(tasks, searchDate, dayStart, dayEnd);
    }
}