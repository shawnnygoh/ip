package ally.command;

import ally.allyexception.AllyException;
import ally.tasklist.TaskList;
import ally.ui.UI;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     * @throws AllyException if an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, UI ui) throws AllyException;

    /**
     * Determines if the command is an exit command.
     *
     * @return true if it is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
