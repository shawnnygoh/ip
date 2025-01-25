package ally.command;

import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) throws AllyException {
        try {
            this.index = Integer.parseInt(input.substring(7).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui) throws AllyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.unmarkAsDone(index);
            ui.showUnmarkedDone(tasks.get(index));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
