package ally.tasklist;

import java.io.IOException;
import java.util.ArrayList;
import ally.storage.*;

/**
 * Manages a list of tasks, including adding, removing, and updating tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList and loads tasks from storage.
     */
    public TaskList() {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTasks();
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the list and saves the updated list to storage.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
        saveToFile();
    }

    /**
     * Saves the task list to storage.
     */
    public void saveToFile() {
        try {
            storage.saveTask(tasks);
        } catch (IOException e) {
            System.out.println("Uh oh! Unable to save tasks to file. 😓");
        }
    }

    /**
     * Gets a task by its index.
     *
     * @param index the index of the task
     * @return the task at the specified index, or null if the index is invalid
     */
    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }

        return null;
    }

    /**
     * Gets the list of all tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Deletes a task by its index and saves the updated list to storage.
     *
     * @param index the index of the task to delete
     * @return the deleted task, or null if the index is invalid
     */
    public Task delete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.remove(index);
            saveToFile();
            return deletedTask;
        }

        return null;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as done by its index and saves the updated list to storage.
     *
     * @param index the index of the task to mark as done
     */
    public void markAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            saveToFile();
        }
    }

    /**
     * Marks a task as not done by its index and saves the updated list to storage.
     *
     * @param index the index of the task to mark as not done
     */
    public void unmarkAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            saveToFile();
        }
    }
}
