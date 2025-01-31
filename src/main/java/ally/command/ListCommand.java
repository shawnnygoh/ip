package ally.command;

import ally.tasklist.TaskList;
import ally.ui.UI;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    private String response = "Command executed";

    /**
     * Executes the command to list all tasks.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        response = sb.toString();
        ui.showTaskList(tasks);
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
