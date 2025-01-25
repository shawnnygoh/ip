package ally.allyexception;

/**
 * Exception thrown when a task name is empty.
 */
public class EmptyTaskNameException extends AllyException {
    /**
     * Constructs an EmptyTaskNameException with a specific task type.
     *
     * @param taskType the type of the task (e.g., todo, deadline, or event).
     */
    public EmptyTaskNameException(String taskType) {
        super("Sorry! The name of a " + taskType + " cannot be empty. 😶");
    }
}
