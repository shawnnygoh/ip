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
    private final String searchKeyword;

    /**
     * Constructs a FindCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the date format is invalid.
     */
    public FindCommand(String input) throws AllyException {
        try {
            String trimmedInput = input.substring(5).trim();
            if (trimmedInput.isEmpty() || trimmedInput.matches(".*\\d{4}-\\d{2}-\\d{2}.*")) {
                this.searchDate = Parser.parseDateTime(trimmedInput);
                this.searchKeyword = null;
            } else {
                this.searchDate = null;
                this.searchKeyword = trimmedInput;
            }
        } catch (DateTimeParseException e) {
            throw new AllyException("Sorry, the date format is invalid. 😵");
        }
    }

    /**
     * Executes the command to find tasks by date or keyword.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        if (searchDate != null) {
            LocalDateTime dayStart = searchDate.withHour(0).withMinute(0);
            LocalDateTime dayEnd = searchDate.withHour(23).withMinute(59);
            ui.showTasksOnDate(tasks, searchDate, dayStart, dayEnd);
        } else if (searchKeyword != null) {
            ui.showTasksByKeyword(tasks, searchKeyword);
        }
    }
}