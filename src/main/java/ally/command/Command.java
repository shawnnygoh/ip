package ally.command;

import ally.allyexception.AllyException;
import ally.tasklist.TaskList;
import ally.ui.Ui;

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
    public abstract void execute(TaskList tasks, Ui ui) throws AllyException;

    /**
     * Gets the response string for the GUI.
     *
     * @return String containing the command's response message
     */
    public abstract String getResponseString();

    /**
     * Determines if the command is an exit command.
     *
     * @return true if it is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
