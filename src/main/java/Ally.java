import java.util.Scanner;
import ToDoList.*;
import AllyException.*;

public class Ally {
    private String name;
    private static final String horizontalDivider = "____________________________________________________________";
    private static final String logo =
            "    _    _ _\n"
                    + "   / \\  | | |_   _\n"
                    + "  / _ \\ | | | | | |\n"
                    + " / ___ \\| | | |_| |\n"
                    + "/_/   \\_\\_|_|\\__, |\n"
                    + "             |___/\n";

    private final Scanner sc;
    private final ToDoList tasks;

    public Ally() {
        this.sc = new Scanner(System.in);
        this.tasks = new ToDoList();
    }

    public void start() {
        printGreeting();
        awaitUserInput();
        sc.close();
    }

    private void printGreeting() {
        System.out.println(horizontalDivider);
        System.out.println(logo);
        System.out.println("Hi! I'm Ally!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider);
    }

    private void awaitUserInput() {
        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.println(horizontalDivider);

            try {
                if (input.equals("list")) {
                    listTasks();
                } else if (input.equals("bye")) {
                    printGoodbye();
                    break;
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (AllyException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontalDivider);
            }
        }
    }

    private void addTodo(String taskName) throws AllyException {
        if (taskName.startsWith("todo")) {
            taskName = taskName.substring(4).trim();
        } else {
            taskName = taskName.trim();
        }

        if (taskName.isEmpty()) {
            throw new EmptyTaskNameException("todo");
        }

        Task task = new Todo(taskName);
        tasks.add(task);
        printConfirmation(task);
    }

    private void addDeadline(String taskName) throws AllyException {
        try {
            String[] parts = taskName.split(" /by ");
            taskName = parts[0].substring(8).trim();

            if (taskName.isEmpty()) {
                throw new EmptyTaskNameException("deadline");
            }

            String deadline = parts[1].trim();
            Task task = new Deadline(taskName, deadline);
            tasks.add(task);
            printConfirmation(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry, the deadline format is invalid. 😵");
            System.out.println(horizontalDivider);
        }
    }

    private void addEvent(String taskName) throws AllyException {
        try {
            String[] parts = taskName.split(" /from ");
            taskName = parts[0].substring(5).trim();

            if (taskName.isEmpty()) {
                throw new EmptyTaskNameException("event");
            }

            String[] time = parts[1].split(" /to ");
            String startTime = time[0].trim();
            String endTime = time[1].trim();
            Task task = new Event(taskName, startTime, endTime);
            tasks.add(task);
            printConfirmation(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry! The event format is invalid. 😵");
            System.out.println(horizontalDivider);
        }
    }

    private void deleteTask(String taskName) throws AllyException {
        try {
            if (taskName.trim().equals("delete")) {
                throw new AllyException("Sorry! The task number cannot be empty. 😶");
            }

            int taskIndex = Integer.parseInt(taskName.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task deletedTask = tasks.delete(taskIndex);
                System.out.println("Noted! I've deleted this task:");
                System.out.println("  " + deletedTask);
                System.out.println("You now have " + tasks.size() + " tasks in the list!");
            } else {
                System.out.println("Sorry! The task number you entered is invalid. 😵");
            }
        } catch (NumberFormatException e) {
            throw new AllyException("Sorry! The task number is invalid. 😵");
        }
        System.out.println(horizontalDivider);
    }

    private void printConfirmation(Task task) {
        System.out.println("Okay! I've added this task. 😇");
        System.out.println("  " + task);
        System.out.println("You now have " + tasks.size() + " task(s) in your list.");
        System.out.println(horizontalDivider);
    }

    private void listTasks() {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(horizontalDivider);
    }

    private void markTask(String taskName) {
        try {
            int taskIndex = Integer.parseInt(taskName.substring(5)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.markAsDone(taskIndex);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(taskIndex).toString());
            } else {
                System.out.println("Sorry! The task number you entered is invalid. 😵");
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Sorry! The task number you entered is invalid. 😵");
        }

        System.out.println(horizontalDivider);
    }

    private void unmarkTask(String taskName) {
        try {
            int taskIndex = Integer.parseInt(taskName.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.markAsNotDone(taskIndex);
                System.out.println("Okay! I've marked this task as not done yet: ");
                System.out.println(tasks.get(taskIndex).toString());
            } else {
                System.out.println("Sorry! The task number you entered is invalid. 😵");
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Sorry! The task number you entered is invalid. 😵");
        }

        System.out.println(horizontalDivider);
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon! 🤗");
        System.out.println(horizontalDivider);
    }

    public static void main(String[] args) {
        new Ally().start();
    }
}