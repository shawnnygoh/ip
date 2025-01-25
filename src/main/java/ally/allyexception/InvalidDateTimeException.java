package ally.allyexception;

public class InvalidDateTimeException extends AllyException {
    public InvalidDateTimeException(String taskType) {
        super("Sorry! The date/time format for the " + taskType + " is invalid. 😵");
    }
}
