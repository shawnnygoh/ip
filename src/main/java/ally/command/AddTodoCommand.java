package ally.command;

import ally.allyexception.AllyException;
import ally.allyexception.EmptyTaskNameException;
import ally.tasklist.Task;
import ally.tasklist.TaskList;
import ally.tasklist.Todo;
import ally.ui.UI;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;
    private String response = "Command executed";

    /**
     * Constructs an AddTodoCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the task name is empty.
     */
    public AddTodoCommand(String input) throws AllyException {
        this.description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyTaskNameException("todo");
        }
    }

    /**
     * Executes the command to add a todo task.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, UI ui) {
        Task task = new Todo(description);
        tasks.add(task);
        response = "Added todo: " + task.toString();
        ui.showTaskAdded(task, tasks.size());
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
