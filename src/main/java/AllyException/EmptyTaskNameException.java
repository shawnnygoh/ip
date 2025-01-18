package AllyException;

public class EmptyTaskNameException extends AllyException {
    public EmptyTaskNameException(String taskType) {
        super("Sorry! The name of a " + taskType + " cannot be empty. 😶");
    }
}
