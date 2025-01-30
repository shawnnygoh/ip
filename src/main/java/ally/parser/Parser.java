package ally.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import ally.allyexception.AllyException;
import ally.allyexception.InvalidCommandFormatException;
import ally.allyexception.UnknownCommandException;
import ally.command.AddDeadlineCommand;
import ally.command.AddEventCommand;
import ally.command.AddTodoCommand;
import ally.command.Command;
import ally.command.DeleteCommand;
import ally.command.ExitCommand;
import ally.command.FindCommand;
import ally.command.ListCommand;
import ally.command.MarkCommand;
import ally.command.UnmarkCommand;

/**
 * Parser for commands and datetime strings.
 */
public class Parser {
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            // Date + Time formats
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-dd HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),

            // Date only formats
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),

            // Day + Time formats
            DateTimeFormatter.ofPattern("E HHmm"),
            DateTimeFormatter.ofPattern("E h:mma"),
            DateTimeFormatter.ofPattern("E ha")
    };

    /**
     * Parses user input into a Command object.
     *
     * @param input String containing user input
     * @return Command object representing the input
     * @throws AllyException if input is invalid or cannot be parsed
     */
    public static Command parseCommand(String input) throws AllyException {
        if (input == null) {
            throw new AllyException("No input provided");
        }

        switch (input.split(" ")[0]) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "find":
            if (input.length() <= 4) {
                throw new InvalidCommandFormatException("find", "Date required");
            }
            return new FindCommand(input);
        case "todo":
            return new AddTodoCommand(input);
        case "deadline":
            return new AddDeadlineCommand(input);
        case "event":
            return new AddEventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param dateStr String containing date/time
     * @return LocalDateTime object
     * @throws DateTimeParseException if string cannot be parsed
     */
    public static LocalDateTime parseDateTime(String dateStr) throws DateTimeParseException {
        if (dateStr.matches("(?i)(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday).*")) {
            return parseDayFormat(dateStr);
        }

        if (isTimeOnly(dateStr)) {
            return parseTimeOnly(dateStr);
        }

        return parseFullDate(dateStr);
    }

    private static LocalDateTime parseDayFormat(String dateStr) {
        String[] parts = dateStr.split(" ", 2);
        DayOfWeek day = DayOfWeek.valueOf(parts[0].toUpperCase());
        LocalDateTime nextDay = LocalDateTime.now().with(TemporalAdjusters.next(day));

        if (parts.length == 1) {
            return nextDay.withHour(0).withMinute(0).withSecond(0);
        }

        String timePart = parts[1].toUpperCase()
                .replace("AM", "").replace("PM", "").trim();

        try {
            int hour;
            int minute;

            if (timePart.contains(":")) {
                String[] timeComponents = timePart.split(":");
                hour = Integer.parseInt(timeComponents[0]);
                minute = Integer.parseInt(timeComponents[1]);
            } else {
                hour = Integer.parseInt(timePart.substring(0, Math.min(2, timePart.length())));
                minute = timePart.length() > 2 ? Integer.parseInt(timePart.substring(2)) : 0;
            }

            if (parts[1].toLowerCase().contains("pm") && hour < 12) {
                hour += 12;
            }
            if (parts[1].toLowerCase().contains("am") && hour == 12) {
                hour = 0;
            }

            return nextDay.withHour(hour).withMinute(minute).withSecond(0);
        } catch (NumberFormatException e) {
            throw new DateTimeParseException("Invalid time format", dateStr, 0);
        }
    }

    /**
     * Checks if string contains only time information.
     *
     * @param timeStr String to check
     * @return true if string is time-only format
     */
    public static boolean isTimeOnly(String timeStr) {
        return timeStr.matches("\\d{1,4}")
                || timeStr.matches("\\d{1,2}:\\d{2}")
                || timeStr.matches("\\d{1,2}(?:am|pm)");
    }

    private static LocalDateTime parseTimeOnly(String dateStr) {
        LocalDateTime today = LocalDateTime.now();
        String fullDateTime = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + dateStr;

        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(fullDateTime, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Invalid time format", dateStr, 0);
    }

    private static LocalDateTime parseFullDate(String dateStr) {
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                try {
                    LocalDate date = LocalDate.parse(dateStr, formatter);
                    return date.atStartOfDay();
                } catch (DateTimeParseException e2) {
                    continue;
                }
            }
        }
        throw new DateTimeParseException("Invalid date format", dateStr, 0);
    }
}
