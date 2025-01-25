package ally.allyexception;

/**
 * Exception thrown when a task number is invalid.
 */
public class InvalidTaskNumberException extends AllyException {
    /**
     * Constructs an InvalidTaskNumberException.
     */
    public InvalidTaskNumberException() {
        super("Sorry! The task number is invalid. 😵");
    }
}
