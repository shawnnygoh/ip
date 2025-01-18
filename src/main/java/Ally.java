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
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
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