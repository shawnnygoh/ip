package ally.command;

import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the task number is invalid.
     */
    public MarkCommand(String input) throws AllyException {
        try {
            this.index = Integer.parseInt(input.substring(5).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     * @throws InvalidTaskNumberException if the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, UI ui) throws InvalidTaskNumberException {
        if (index >= 0 && index < tasks.size()) {
            tasks.markAsDone(index);
            ui.showMarkedDone(tasks.get(index));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}