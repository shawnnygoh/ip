package ally.command;

import ally.tasklist.*;
import ally.ui.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}