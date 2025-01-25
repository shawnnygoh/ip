package ally.command;

import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

public class DeleteCommand extends Command {
    private final int index;

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