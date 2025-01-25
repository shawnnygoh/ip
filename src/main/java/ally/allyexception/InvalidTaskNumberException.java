package ally.allyexception;

public class InvalidTaskNumberException extends AllyException {
    public InvalidTaskNumberException() {
        super("Sorry! The task number is invalid. 😵");
    }
}
