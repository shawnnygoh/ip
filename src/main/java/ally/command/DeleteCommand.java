package ally.command;

import ally.allyexception.AllyException;
import ally.allyexception.InvalidCommandFormatException;
import ally.allyexception.InvalidTaskNumberException;
import ally.tasklist.Task;
import ally.tasklist.TaskList;
import ally.ui.UI;

/**
 * Command to delete a task by its index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the task number is invalid or missing.
     */
    public DeleteCommand(String input) throws AllyException {
        try {
            if (input.trim().equals("delete")) {
                throw new InvalidCommandFormatException("delete", "Task number required");
            }
            this.index = Integer.parseInt(input.substring(7).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Executes the command to delete a task.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     * @throws InvalidTaskNumberException if the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, UI ui) throws InvalidTaskNumberException {
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.delete(index);
            ui.showTaskDeleted(deletedTask, tasks.size());
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
