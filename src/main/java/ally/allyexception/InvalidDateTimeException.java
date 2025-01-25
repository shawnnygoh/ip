package ally.allyexception;

/**
 * Exception thrown when a date or time format is invalid.
 */
public class InvalidDateTimeException extends AllyException {
    /**
     * Constructs an InvalidDateTimeException with a specific task type.
     *
     * @param taskType the type of the task.
     */
    public InvalidDateTimeException(String taskType) {
        super("Sorry! The date/time format for the " + taskType + " is invalid. 😵");
    }
}
