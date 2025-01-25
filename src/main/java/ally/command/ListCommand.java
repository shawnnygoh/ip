package ally.command;

import ally.tasklist.*;
import ally.ui.*;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.showTaskList(tasks);
    }
}
