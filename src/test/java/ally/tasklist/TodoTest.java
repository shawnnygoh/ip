package ally.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("Read book");
        String expectedOutput = "[T][ ] Read book";
        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void testToStringWithCompletion() {
        Todo todo = new Todo("Read book");
        todo.markAsDone();
        String expectedOutput = "[T][X] Read book";
        assertEquals(expectedOutput, todo.toString());
    }
}
