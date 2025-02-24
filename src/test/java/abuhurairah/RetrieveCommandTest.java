package abuhurairah;

import abuhurairah.command.RetrieveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import abuhurairah.task.Deadline;
import abuhurairah.task.Task;
import java.util.ArrayList;
import java.util.List;

public class RetrieveCommandTest {

    private ArrayList<Task> tasks;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
    }

    @Test
    public void testPrintListWithEmptyTaskList() {
        // Test printing when the task list is empty
        String result = RetrieveCommand.printList(tasks);
        assertEquals("", result);
    }

    @Test
    public void testPrintListWithMultipleTasks() {
        // Add some tasks to the list
        Task task1 = new Deadline("Task 1", "2025-01-01 10:00");
        Task task2 = new Deadline("Task 2", "2025-02-01 10:00");
        tasks.add(task1);
        tasks.add(task2);

        // Test printing tasks in the list
        String result = RetrieveCommand.printList(tasks);
        assertTrue(result.contains("Task 1"));
        assertTrue(result.contains("Task 2"));
    }

    @Test
    public void testGetOverdueTaskWithNoOverdueTasks() {
        // Test the case when no tasks are overdue
        Task task1 = new Deadline("Task 1", "2025-01-01 10:00");
        task1.setComplete(true);
        tasks.add(task1);

        String result = RetrieveCommand.getOverdueTask("OVERDUE", tasks);
        assertEquals("No overdue tasks!!", result);
    }

    @Test
    public void testGetOverdueTaskWithOverdueTasks() {
        // Test the case when there are overdue tasks
        Task task1 = new Deadline("Overdue Task", "2020-01-01 10:00");
        tasks.add(task1);

        String result = RetrieveCommand.getOverdueTask("OVERDUE", tasks);
        assertTrue(result.contains("Overdue Task"));
    }

    @Test
    public void testListTasksWithEmptyList() {
        // Test when the task list is empty
        String result = RetrieveCommand.listTasks(tasks);
        assertEquals("No new tasks, YAY", result);
    }

    @Test
    public void testListTasksWithNonEmptyList() {
        // Add tasks to the list
        Task task1 = new Deadline("Task 1", "2025-01-01 10:00");
        tasks.add(task1);

        // Test when the task list is not empty
        String result = RetrieveCommand.listTasks(tasks);
        assertTrue(result.contains("That's like not even that many! you got this!"));
        assertTrue(result.contains("Task 1"));
    }

    @Test
    public void testFindTaskWithMatchingDescription() {
        // Add tasks to the list
        Task task1 = new Deadline("Complete project", "2025-01-01 10:00");
        tasks.add(task1);

        // Test finding a task with a matching description
        String result = RetrieveCommand.findTask("Complete", tasks);
        assertTrue(result.contains("Complete project"));
    }

    @Test
    public void testFindTaskWithNoMatchingDescription() {
        // Add tasks to the list
        Task task1 = new Deadline("Complete project", "2025-01-01 10:00");
        tasks.add(task1);

        // Test searching for a non-existing task
        String result = RetrieveCommand.findTask("Nonexistent", tasks);
        assertEquals("no such item!!!", result);
    }

    @Test
    public void testFindTaskWithEmptyDescription() {
        // Test searching with an empty description
        String result = RetrieveCommand.findTask("", tasks);
        assertEquals("no such item!!!", result);
    }
}

