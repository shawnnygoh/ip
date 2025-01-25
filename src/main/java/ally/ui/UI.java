package ally.ui;

import java.util.*;
import ally.tasklist.*;
import java.time.*;
import java.time.format.*;

public class UI {
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

    public UI() {
        this.scanner = new Scanner(System.in);
    }

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

    public void showError(String message) {
        System.out.println(message);
        showDivider();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Okay! I've added this task. 😇");
        System.out.println("  " + task);
        System.out.println("You now have " + totalTasks + " task(s) in your list.");
        showDivider();
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showDivider();
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println("Noted! I've deleted this task:");
        System.out.println("  " + task);
        System.out.println("You now have " + remainingTasks + " tasks in the list!");
        showDivider();
    }

    public void showMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showDivider();
    }

    public void showUnmarkedDone(Task task) {
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(task);
        showDivider();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon! 🤗");
        showDivider();
    }

    public void showTasksOnDate(TaskList tasks, LocalDateTime date, LocalDateTime dayStart, LocalDateTime dayEnd) {
        System.out.println("Here are the tasks on " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");

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
            return !event.getStartTime().isAfter(dayEnd) &&
                    !event.getEndTime().isBefore(dayStart);
        }
        return false;
    }
}



