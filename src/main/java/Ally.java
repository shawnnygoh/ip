import java.util.Scanner;

public class Ally {
    private String name;
    private static final String horizontalDivider = "____________________________________________________________";
    private static final String logo =
              "    _    _ _       \n"
            + "   / \\  | | |_   _ \n"
            + "  / _ \\ | | | | | |\n"
            + " / ___ \\| | | |_| |\n"
            + "/_/   \\_\\_|_|\\__, |\n"
            + "             |___/ \n";
    private final Scanner sc;
    private final ToDoList tasks;

    public Ally() {
        this.sc = new Scanner(System.in);
        this.tasks = new ToDoList();
    }

    public void start() {
        printGreeting();
        awaitUserInput();
        printGoodbye();
        sc.close();
    }

    private void printGreeting() {
        System.out.println(horizontalDivider);
        System.out.println(logo);
        System.out.println("Hello! I'm Ally!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider);
    }

    private void awaitUserInput() {
        String input;
        while (true) {
            input = sc.nextLine();
            System.out.println(horizontalDivider);

            if (input.equals("list")) {
                listTasks();
            } else if (input.equals("bye")) {
                break;
            } else if (input.startsWith("mark")) {
                markTask(input);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input);
            } else {
                addTask(input);
            }
        }
    }

    private void addTask(String taskName) {
        tasks.add(taskName);
        System.out.println("added: " + taskName);
        System.out.println(horizontalDivider);
    }

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
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
                System.out.println("Sorry, the task number you entered is invalid.");
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Sorry, the task number you entered is invalid.");
        }

        System.out.println(horizontalDivider);
    }

    private void unmarkTask(String taskName) {
        try {
            int taskIndex = Integer.parseInt(taskName.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.markAsNotDone(taskIndex);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(tasks.get(taskIndex).toString());
            } else {
                System.out.println("Sorry, the task number you entered is invalid.");
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Sorry, the task number you entered is invalid.");
        }

        System.out.println(horizontalDivider);
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }

    public static void main(String[] args) {
        new Ally().start();
    }
}