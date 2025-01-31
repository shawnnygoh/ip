package ally.command;

import ally.tasklist.TaskList;
import ally.ui.UI;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.showGoodbye();
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getResponseString() {
        return "Bye. Hope to see you again soon! 🤗";
    }
}
