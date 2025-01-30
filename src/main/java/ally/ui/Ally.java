package ally.ui;

import ally.allyexception.AllyException;
import ally.command.Command;
import ally.parser.Parser;
import ally.tasklist.TaskList;

/**
 * Main class that runs the Ally chatbot application.
 */
public class Ally {
    private final TaskList tasks;
    private final UI ui;

    /**
     * Creates a new Ally chatbot instance with UI and a task list.
     */
    public Ally() {
        this.ui = new UI();
        this.tasks = new TaskList();
    }

    /**
     * * Starts the chatbot application.
     */
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
