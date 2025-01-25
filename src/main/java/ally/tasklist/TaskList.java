package ally.tasklist;

import java.io.IOException;
import java.util.ArrayList;
import ally.storage.*;

public class TaskList {
    private ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList() {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTasks();
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
        }
    }

    public void add(Task task) {
        tasks.add(task);
        saveToFile();
    }

    public void saveToFile() {
        try {
            storage.saveTask(tasks);
        } catch (IOException e) {
            System.out.println("Uh oh! Unable to save tasks to file. 😓");
        }
    }

    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }

        return null;
    }

    public Task delete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.remove(index);
            saveToFile();
            return deletedTask;
        }

        return null;
    }

    public int size() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            saveToFile();
        }
    }

    public void unmarkAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            saveToFile();
        }
    }
}
