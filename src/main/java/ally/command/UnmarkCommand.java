package ally.command;

import ally.allyexception.AllyException;
import ally.allyexception.InvalidTaskNumberException;
import ally.tasklist.TaskList;
import ally.ui.Ui;

/**
 * Command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private String response = "Command executed";
    private final int index;

    /**
     * Constructs an UnmarkCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the task number is invalid.
     */
    public UnmarkCommand(String input) throws AllyException {
        try {
            this.index = Integer.parseInt(input.substring(7).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Executes the command to unmark a task as done.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     * @throws AllyException if the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws AllyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.unmarkAsDone(index);
            response = "Unmarked task: " + tasks.get(index).toString();
            ui.showUnmarkedDone(tasks.get(index));
        } else {
            throw new InvalidTaskNumberException();
        }
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
