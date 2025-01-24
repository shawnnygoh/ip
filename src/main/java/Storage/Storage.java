package Storage;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import ToDoList.*;

public class Storage {
    private static final String FILE_PATH = "data/ally.txt";

    public void saveTask(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get("data"));

        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task task : tasks) {
            fw.write(encodeTask(task) + "\n");
        }

        fw.close();
    }

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
        String baseEncoding = task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();

        if (task instanceof Deadline) {
            return baseEncoding + " | " + ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            return baseEncoding + " | " + ((Event) task).getTime();
        }

        return baseEncoding;
    }

    private Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Uh oh! Invalid task format. 😓");
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
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Uh oh! Invalid event format. 😓");
            }
            task = new Event(parts[2], parts[3], parts[4]);
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