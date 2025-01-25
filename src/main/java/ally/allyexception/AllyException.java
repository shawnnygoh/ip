package ally.allyexception;

/**
 * Custom exception class for handling specific exceptions in the Ally application.
 */
public class AllyException extends Exception {
    /**
     * Constructs an AllyException with the specified message.
     *
     * @param message the specified message.
     */
    public AllyException(String message) {
        super(message);
    }
}
