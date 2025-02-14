package abuhurairah;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {
    @TempDir // JUnit5 Temporary Directory (auto-deletes after test)
    File tempDir;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        File tempFile = new File(tempDir, "testStorage.txt");
        storage = new Storage(tempFile.getAbsolutePath());
        taskList = new TaskList();
    }

    @Test
    public void getFile_createsValidFile() {
        File file = storage.getFile();
        assertEquals("testStorage.txt", file.getName());
    }

    @Test
    public void store_savesTasksToFile() throws IOException {
        taskList.argumentHandling("todo Read book", 0, taskList);
        taskList.argumentHandling("EvenT cry /from now /to later", 0, taskList);

        storage.store(taskList);
        File file = storage.getFile();

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void getStore_loadsTasksFromFile() {
        taskList.argumentHandling("todo Read book", 0, taskList);
        taskList.argumentHandling("EvenT cry /from now /to later", 0, taskList);
        storage.store(taskList);

        TaskList loadedList = new TaskList();
        int unDoneCount = storage.getStore(storage.getPath(), loadedList, "TestUser");

        List<Task> tasks = loadedList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] Read book", tasks.get(0).toString());
        assertEquals("[E][ ] cry (from: now to: later)", tasks.get(1).toString());
    }
}
