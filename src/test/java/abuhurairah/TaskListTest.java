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