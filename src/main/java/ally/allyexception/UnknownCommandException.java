package ally.allyexception;

/**
 * Exception thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends AllyException {
    /**
     * Constructs an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("Sorry! I don't know what that means. 😓");
    }
}
