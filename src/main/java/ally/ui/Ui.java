package ally.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ally.tasklist.Deadline;
import ally.tasklist.Event;
import ally.tasklist.Task;
import ally.tasklist.TaskList;

/**
 * Handles user interface operations for the Ally chatbot.
 */
public class Ui {
    private static final String HORIZONTAL_DIVIDER = "____________________________________________________________";
    private static final String LOGO =
            """
                        _    _ _
                       / \\  | | |_   _
                      / _ \\ | | | | | |
                     / ___ \\| | | |_| |
                    /_/   \\_\\_|_|\\__, |
                                 |___/
                    """;
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message with logo.
     */
    public void showWelcome() {
        showDivider();
        System.out.println(LOGO);
        System.out.println("Hi! I'm Ally!");
        System.out.println("What can I do for you?");
        showDivider();
    }

    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showDivider() {
        System.out.println(HORIZONTAL_DIVIDER);
    }

    /**
     * Shows error message to user.
     *
     * @param message Error message to display
     */
    public void showError(String message) {
        System.out.println(message);
        showDivider();
    }

    /**
     * Shows confirmation of task addition.
     *
     * @param task The task that was added
     * @param totalTasks Current total number of tasks
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Okay! I've added this task. 😇");
        System.out.println("  " + task);
        System.out.println("You now have " + totalTasks + " task(s) in your list.");
        showDivider();
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks TaskList containing all tasks
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showDivider();
    }

    /**
     * Shows confirmation of task deletion.
     *
     * @param task The task that was deleted
     * @param remainingTasks Number of tasks remaining after deletion
     */
    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println("Noted! I've deleted this task:");
        System.out.println("  " + task);
        System.out.println("You now have " + remainingTasks + " tasks in the list!");
        showDivider();
    }

    /**
     * Shows confirmation that a task was marked as done.
     *
     * @param task The task that was marked as done
     */
    public void showMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showDivider();
    }

    /**
     * Shows confirmation that a task was unmarked.
     *
     * @param task The task that was unmarked
     */
    public void showUnmarkedDone(Task task) {
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(task);
        showDivider();
    }

    /**
     * Displays goodbye message when exiting.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon! 🤗");
        showDivider();
    }

    /**
     * Shows all tasks occurring on a specific date.
     *
     * @param tasks TaskList to search through
     * @param date The date to search for
     * @param dayStart Start of the day
     * @param dayEnd End of the day
     */
    public void showTasksOnDate(TaskList tasks, LocalDateTime date, LocalDateTime dayStart, LocalDateTime dayEnd) {
        System.out.println("Here are the tasks on "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (isTaskOnDate(task, dayStart, dayEnd)) {
                System.out.println((++count) + "." + task);
            }
        }
        showDivider();
    }

    private boolean isTaskOnDate(Task task, LocalDateTime dayStart, LocalDateTime dayEnd) {
        if (task instanceof Deadline deadline) {
            LocalDateTime dueDate = deadline.getDate();
            return !dueDate.isBefore(dayStart) && !dueDate.isAfter(dayEnd);
        } else if (task instanceof Event event) {
            return !event.getStartTime().isAfter(dayEnd)
                    && !event.getEndTime().isBefore(dayStart);
        }
        return false;
    }

    /**
     * Shows all tasks containing a specific keyword.
     *
     * @param tasks TaskList to search through
     * @param keyword Keyword to search for in task descriptions
     */
    public void showTasksByKeyword(TaskList tasks, String keyword) {
        showDivider();
        System.out.println("Here are the matching tasks in your list:");
        int taskCount = 1;

        for (Task task : tasks.getAll()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(taskCount + "." + task);
                taskCount++;
            }
        }

        if (taskCount == 1) {
            System.out.println("No matching tasks found.");
        }

        showDivider();
    }

    /**
     * Displays a help page.
     */
    public void showHelp() {
        System.out.println("Here are the list of available commands and how to use them:");
        showDivider();
    }
}
