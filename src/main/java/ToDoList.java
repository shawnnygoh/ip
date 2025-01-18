public class ToDoList {
    private final Task[] tasks;
    private int taskCount;

    public ToDoList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void add(String taskName) {
        if (taskCount < 100) {
            tasks[taskCount] = new Task(taskName);
            taskCount++;
        }
    }

    public Task get(int index) {
        if (index >= 0 && index < taskCount) {
            return tasks[index];
        }

        return null;
    }

    public int size() {
        return taskCount;
    }

    public void markAsDone(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
        }
    }

    public void markAsNotDone(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
        }
    }
}
