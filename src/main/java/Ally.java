import ally.allyexception.*;
import ally.parser.*;
import ally.ui.*;
import ally.tasklist.*;
import ally.command.*;

public class Ally {
    private final TaskList tasks;
    private final UI ui;

    public Ally() {
        this.ui = new UI();
        this.tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input == null) {
                    break;
                }
                Command c = Parser.parseCommand(input);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (AllyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Ally().run();
    }
}