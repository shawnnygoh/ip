package ally.command;

import ally.tasklist.TaskList;
import ally.ui.UI;

/**
 * Command to access help page.
 */
public class HelpCommand extends Command {
    private String response = "Here are a list of the available commands:\n\n"
            + "1. todo <description> - Create a new todo task\n"
            + "2. deadline <description> /by <datetime> - Create a new task with deadline\n"
            + "3. event <description> /from <start-time> /to <end-time> - Create a new event\n"
            + "4. list - Show all tasks\n"
            + "5. find <keyword/date> - Search tasks by keyword or date\n"
            + "6. mark <task-number> - Mark a task as done\n"
            + "7. unmark <task-number> - Mark a task as not done\n"
            + "8. delete <task-number> - Delete a task\n"
            + "9. bye - Exit the application";

    /**
     * Executes the command to access the help page.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.showHelp();
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
