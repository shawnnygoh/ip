package ally.command;

import ally.tasklist.*;
import ally.ui.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui) {
        ui.showTaskList(tasks);
    }
}
