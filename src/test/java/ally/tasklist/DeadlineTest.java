package ally.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime deadlineTime = LocalDateTime.of(2024, 2, 14, 18, 30);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);
        String expectedOutput = "[D][ ] Submit assignment (by: Feb 14 2024 6:30pm)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testToStringWithMidnight() {
        LocalDateTime deadlineTime = LocalDateTime.of(2024, 2, 15, 0, 0);
        Deadline deadline = new Deadline("Finish report", deadlineTime);
        String expectedOutput = "[D][ ] Finish report (by: Feb 15 2024 12am)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testToStringWithNoMinutes() {
        LocalDateTime deadlineTime = LocalDateTime.of(2024, 2, 15, 13, 0);
        Deadline deadline = new Deadline("Lunch meeting", deadlineTime);
        String expectedOutput = "[D][ ] Lunch meeting (by: Feb 15 2024 1pm)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testGetType() {
        LocalDateTime deadlineTime = LocalDateTime.of(2024, 2, 14, 18, 0);
        Deadline deadline = new Deadline("Submit assignment", deadlineTime);
        assertEquals("D", deadline.getType());
    }
}
