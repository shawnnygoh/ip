package ally.tasklist;

/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified name.
     *
     * @param taskName the name of the task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
