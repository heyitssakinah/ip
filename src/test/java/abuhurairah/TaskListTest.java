package abuhurairah;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        // Capture system output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out; // Save original System.out
        System.setOut(new PrintStream(outputStream)); // Redirect output
    }

    @Test
    public void argumentHandling_addTodoTask_taskAdded() {
        int initialCount = taskList.getTasks().size();
        taskList.argumentHandling("todo Read book", 0, false);
        assertEquals(initialCount + 1, taskList.getTasks().size());
        assertInstanceOf(Todo.class, taskList.getTasks().get(0));
    }

    @Test
    public void argumentHandling_findItem_exists() {
        taskList.argumentHandling("todo Read book", 0, false);
        taskList.argumentHandling("todo eat book", 0, false);
        taskList.argumentHandling("find book", 1, true);
        String outputs = outputStream.toString();
        System.out.println("Captured Output: " + outputs);
        String expectedOutput = "Read book";
        assertTrue(outputs.contains(expectedOutput));
    }

    @Test
    public void argumentHandling_findItem_doesNotExists() {
        taskList.argumentHandling("todo Read", 0, false);
        taskList.argumentHandling("find book", 1, true);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("no such item!!!"));
    }

    @Test
    public void argumentHandling_listCommand_correctOutput() {
        taskList.argumentHandling("list", 0, true);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("No new tasks, YAY"));
    }

    @Test
    public void argumentHandling_invalidCommand_errorHandled() {
        int initialCount = taskList.getTasks().size();
        taskList.argumentHandling("invalidCommand test", 0, false);
        assertEquals(initialCount, taskList.getTasks().size()); // Should not add a task
    }
}