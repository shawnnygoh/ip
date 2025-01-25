package ally.allyexception;

public class UnknownCommandException extends AllyException {
    public UnknownCommandException() {
        super("Sorry! I don't know what that means. 😓");
    }
}
