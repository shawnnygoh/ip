package ToDoList;

import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<Task> tasks;

    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }

        return null;
    }

    public Task delete(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }

        return null;
    }

    public int size() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        }
    }

    public void markAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
        }
    }
}
