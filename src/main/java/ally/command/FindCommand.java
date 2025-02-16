package ally.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ally.allyexception.AllyException;
import ally.parser.Parser;
import ally.tasklist.Deadline;
import ally.tasklist.Event;
import ally.tasklist.Task;
import ally.tasklist.TaskList;
import ally.ui.Ui;

/**
 * Command to find tasks.
 */
public class FindCommand extends Command {
    private String response = "Command executed";
    private final LocalDateTime searchDate;
    private final String searchKeyword;

    /**
     * Constructs a FindCommand from the user input.
     *
     * @param input the user input string.
     * @throws AllyException if the date format is invalid.
     */
    public FindCommand(String input) throws AllyException {
        try {
            String trimmedInput = input.substring(5).trim();
            if (trimmedInput.isEmpty() || trimmedInput.matches(".*\\d{4}-\\d{2}-\\d{2}.*")) {
                this.searchDate = Parser.parseDateTime(trimmedInput);
                this.searchKeyword = null;
            } else {
                this.searchDate = null;
                this.searchKeyword = trimmedInput;
            }
        } catch (DateTimeParseException e) {
            throw new AllyException("Sorry, the date format is invalid. 😵");
        }
    }

    /**
     * Executes the command to find tasks by date or keyword.
     *
     * @param tasks the task list.
     * @param ui    the user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        StringBuilder sb = new StringBuilder();
        if (searchDate != null) {
            LocalDateTime dayStart = searchDate.withHour(0).withMinute(0);
            LocalDateTime dayEnd = searchDate.withHour(23).withMinute(59);
            sb.append("Tasks on ").append(searchDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).append(":\n");

            int count = 0;
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                boolean isTaskOnDate = false;

                if (task instanceof Deadline deadline) {
                    LocalDateTime dueDate = deadline.getDate();
                    isTaskOnDate = !dueDate.isBefore(dayStart) && !dueDate.isAfter(dayEnd);
                } else if (task instanceof Event event) {
                    isTaskOnDate = !event.getStartTime().isAfter(dayEnd)
                            && !event.getEndTime().isBefore(dayStart);
                }

                if (isTaskOnDate) {
                    ++count;
                    sb.append(count).append(". ").append(task).append("\n");
                }
            }

            ui.showTasksOnDate(tasks, searchDate, dayStart, dayEnd);
        } else if (searchKeyword != null) {
            sb.append("Tasks matching '").append(searchKeyword).append("':\n");

            int taskCount = 1;
            for (Task task : tasks.getAll()) {
                if (task.getDescription().toLowerCase().contains(searchKeyword.toLowerCase())) {
                    sb.append(taskCount).append(".").append(task).append("\n");
                    taskCount++;
                }
            }

            if (taskCount == 1) {
                sb.append("No matching tasks found.");
            }

            ui.showTasksByKeyword(tasks, searchKeyword);
        }
        response = sb.toString();
    }

    @Override
    public String getResponseString() {
        return response;
    }
}
