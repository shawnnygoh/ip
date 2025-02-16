package ally.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import ally.allyexception.AllyException;
import ally.allyexception.EmptyTaskNameException;
import ally.allyexception.InvalidCommandFormatException;
import ally.allyexception.InvalidDateTimeException;
import ally.parser.Parser;
import ally.tasklist.Deadline;
import ally.tasklist.Task;
import ally.tasklist.TaskList;
import ally.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime deadline;

    private String response = "Command executed";

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
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(description, deadline);
        tasks.add(task);
        response = "Added deadline: " + task.toString();
        ui.showTaskAdded(task, tasks.size());
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
