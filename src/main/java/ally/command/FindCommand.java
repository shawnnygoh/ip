package ally.command;

import java.time.*;
import java.time.format.*;

import ally.parser.*;
import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

/**
 * Command to find tasks.
 */
public class FindCommand extends Command {
    private final LocalDateTime searchDate;

    /**
     * Constructs a FindCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the date format is invalid.
     */
    public FindCommand(String input) throws AllyException {
        try {
            String dateStr = input.substring(5).trim();
            this.searchDate = Parser.parseDateTime(dateStr);
        } catch (DateTimeParseException e) {
            throw new AllyException("Sorry, the date format is invalid. 😵");
        }
    }

    /**
     * Executes the command to find tasks by date.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        LocalDateTime dayStart = searchDate.withHour(0).withMinute(0);
        LocalDateTime dayEnd = searchDate.withHour(23).withMinute(59);
        ui.showTasksOnDate(tasks, searchDate, dayStart, dayEnd);
    }
}