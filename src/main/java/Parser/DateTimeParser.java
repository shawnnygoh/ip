package Parser;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

public class DateTimeParser {
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
            DateTimeFormatter.ofPattern("E HHmm"),
            DateTimeFormatter.ofPattern("E h:mma"),
            DateTimeFormatter.ofPattern("E ha")
    };

    private static final DateTimeFormatter[] TIME_FORMATTERS = {
            DateTimeFormatter.ofPattern("H:mm"),
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("ha"),
            DateTimeFormatter.ofPattern("h:mma")
    };

    public static LocalDateTime parse(String dateStr) throws DateTimeParseException {
        if (dateStr.matches("(?i)(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday).*")) {
            String[] parts = dateStr.split(" ", 2);
            DayOfWeek day = DayOfWeek.valueOf(parts[0].toUpperCase());
            LocalDateTime nextDay = LocalDateTime.now().with(TemporalAdjusters.next(day));

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
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DateTimeParseException("Invalid time format", dateStr, 0);
            }
        }

        if (!dateStr.contains("/") && !dateStr.contains("-")) {
            LocalDateTime today = LocalDateTime.now();
            String fullDateTime = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + dateStr;

            for (DateTimeFormatter formatter : TIME_FORMATTERS) {
                try {
                    return LocalDateTime.parse(fullDateTime, formatter);
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }

        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Invalid date format", dateStr, 0);
    }
}