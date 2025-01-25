package ally.command;

import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String input) throws AllyException {
        this.description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyTaskNameException("todo");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui) {
        Task task = new Todo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }
}