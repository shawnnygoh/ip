package ally.command;

import ally.allyexception.*;
import ally.tasklist.*;
import ally.ui.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui) throws AllyException;
    public boolean isExit() {
        return false;
    }
}