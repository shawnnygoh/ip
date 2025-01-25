package ally.command;

import java.time.*;
import java.time.format.DateTimeParseException;

import ally.parser.*;
import ally.tasklist.*;
import ally.allyexception.*;
import ally.ui.*;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime deadline;

    /**
     * Constructs an AddDeadlineCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the input format is invalid or the task name is empty.
     */
    public AddDeadlineCommand(String input) throws AllyException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new InvalidCommandFormatException("deadline", "Missing '/by' time");
        }

        this.description = parts[0].substring(8).trim();
        if (description.isEmpty()) {
            throw new EmptyTaskNameException("deadline");
        }

        try {
            this.deadline = Parser.parseDateTime(parts[1].trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("deadline");
        }
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        Task task = new Deadline(description, deadline);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }
}