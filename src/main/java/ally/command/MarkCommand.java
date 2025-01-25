package ally.command;

import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws AllyException {
        try {
            this.index = Integer.parseInt(input.substring(5).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui) throws InvalidTaskNumberException {
        if (index >= 0 && index < tasks.size()) {
            tasks.markAsDone(index);
            ui.showMarkedDone(tasks.get(index));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}