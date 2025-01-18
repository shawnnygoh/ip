public class ToDoList {
    private final String[] tasks;
    private int taskCount;

    public ToDoList() {
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    public void add(String task) {
        if (taskCount < 100) {
            tasks[taskCount] = task;
            taskCount++;
        }
    }

    public String get(int index) {
        if (index >= 0 && index < taskCount) {
            return tasks[index];
        }

        return null;
    }

    public int size() {
        return taskCount;
    }
}
