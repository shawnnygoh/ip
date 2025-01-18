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
    private static Scanner sc = new Scanner(System.in);

    public static void start() {
        printGreeting();
        awaitUserInput();
        printGoodbye();
        sc.close();
    }

    private static void printGreeting() {
        System.out.println(horizontalDivider);
        System.out.println(logo);
        System.out.println("Hello! I'm Ally!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider);
    }

    private static void awaitUserInput() {
        String input;
        while (true) {
            input = sc.nextLine();
            System.out.println(horizontalDivider);
            System.out.println(" " + input);
            System.out.println(horizontalDivider);

            if (input.equals("bye")) {
                break;
            }
        }
    }

    private static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }

    public static void main(String[] args) {
        start();
    }
}