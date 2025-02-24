package abuhurairah;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import abuhurairah.storage.Parser;
import abuhurairah.storage.Storage;
import abuhurairah.task.Event;
import abuhurairah.task.Task;
import abuhurairah.task.TaskList;

public class StorageTest {
    @TempDir // JUnit5 Temporary Directory (auto-deletes after test)
    File tempDir;
    private Storage storage;
    private TaskList taskList;
    private int taskCount;

    @BeforeEach
    public void setUp() {
        File tempFile = new File(tempDir, "testStorage.txt");
        storage = new Storage(tempFile.getAbsolutePath());
        taskList = new TaskList();
        taskCount = 0;
    }

    @Test
    public void getFile_createsValidFile() {
        File file = storage.getFile();
        assertEquals("testStorage.txt", file.getName());
    }

    @Test
    public void store_savesTasksToFile() throws IOException {
        taskList.argumentHandling("todo Read book");
        taskList.argumentHandling("EvenT cry /from now /to later");

        storage.store(taskList);
        File file = storage.getFile();

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void getStore_loadsTasksFromFile() {
        taskList.argumentHandling("todo Read book");
        taskList.argumentHandling("EvenT cry /from now /to later");
        storage.store(taskList);

        TaskList loadedList = new TaskList();
        storage.getStore(loadedList);

        List<Task> tasks = loadedList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] Read book", tasks.get(0).toString());
        assertEquals("[E][ ] cry (from: now to: later)", tasks.get(1).toString());
    }

    @Test
    public void testTextToArrayListMarkedEvent() {
        String input = "[E][X] cry (from: now to: later)";
        Parser.textToArrayList(input, taskList, 0);

        // Check that taskList contains an Event task
        assertTrue(taskList.getTasks().get(0) instanceof Event);
        Event eventTask = (Event) taskList.getTasks().get(0);
        assertTrue(eventTask.isComplete()); // Check that the task is marked as complete
    }
}
