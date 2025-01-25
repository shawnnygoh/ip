package ally.allyexception;

public class InvalidCommandFormatException extends AllyException {
    public InvalidCommandFormatException(String command, String reason) {
        super("Sorry! The " + command + " format is invalid: " + reason + " 😵");
    }
}
