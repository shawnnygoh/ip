package ally.allyexception;

/**
 * Exception thrown when a command format is invalid.
 */
public class InvalidCommandFormatException extends AllyException {
    /**
     * Constructs an InvalidCommandFormatException with the command and reason for invalidity.
     *
     * @param command the invalid command.
     * @param reason  the reason for invalidity.
     */
    public InvalidCommandFormatException(String command, String reason) {
        super("Sorry! The " + command + " format is invalid: " + reason + " 😵");
    }
}
