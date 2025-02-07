package ally.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ally.parser.Parser;
import ally.tasklist.Deadline;
import ally.tasklist.Event;
import ally.tasklist.Task;
import ally.tasklist.Todo;

/**
 * Handles file storage operations for tasks, including saving to and loading from a file.
 */
public class Storage {
    private static final String FILE_PATH = "data/ally.txt";

    /**
     * Saves a list of tasks to a file.
     * Creates directories if they don't exist.
     *
     * @param tasks ArrayList of tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void saveTask(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get("data"));

        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task task : tasks) {
            fw.write(encodeTask(task) + "\n");
        }

        fw.close();
    }

    /**
     * Loads tasks from a file.
     * Creates a new empty list if the file doesn't exist.
     *
     * @return ArrayList of tasks loaded from file
     * @throws IOException if there is an error reading from the file
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(Paths.get(FILE_PATH))) {
            return tasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            try {
                tasks.add(decodeTask(line));
            } catch (IllegalArgumentException e) {
                System.out.println("Uh oh! Skipping corrupted data: " + line);
            }
        }

        br.close();

        return tasks;
    }

    private String encodeTask(Task task) {
        String baseEncoding = String.format("%s | %s | %s",
                task.getType(),
                task.isDone() ? "1" : "0",
                task.getDescription());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (task instanceof Deadline deadline) {
            return baseEncoding + " | " + deadline.getDate().format(formatter);
        } else if (task instanceof Event event) {
            return String.format("%s | %s | %s",
                    baseEncoding,
                    event.getStartTime().format(formatter),
                    event.getEndTime().format(formatter));
        }

        return baseEncoding;
    }

    private Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        assert parts.length == 3 : "Task data should have at least 3 parts";

        if (parts.length < 3) {
            throw new IllegalArgumentException("Uh oh! Invalid task format. 😵");
        }

        boolean isDone = parts[1].equals("1");
        Task task;

        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;

        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Uh oh! Invalid deadline format. 😓");
            }
            LocalDateTime deadline = Parser.parseDateTime(parts[3]);
            task = new Deadline(parts[2], deadline);
            break;

        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Uh oh! Invalid event format. 😓");
            }
            LocalDateTime startTime = Parser.parseDateTime(parts[3]);
            LocalDateTime endTime = Parser.parseDateTime(parts[4]);
            task = new Event(parts[2], startTime, endTime);
            break;

        default:
            throw new IllegalArgumentException("Uh oh! Unknown task type. 😓");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
