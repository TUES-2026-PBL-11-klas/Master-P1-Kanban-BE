package Master.Kanban.service;

import Master.Kanban.model.Task;
import Master.Kanban.model.State;
import Master.Kanban.model.User;
import Master.Kanban.repository.KanbanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KanbanServiceImplementationTest {

    @Mock
    private KanbanRepository kanbanRepo;

    @InjectMocks
    private KanbanServiceImplementation kanbanService;

    // getAllUserTasks
    @Test
    void shouldReturnOnlyTasksForUserAndNotDeleted() {
        User user1 = new User();
        user1.setToken(100);

        User user2 = new User();
        user2.setToken(200);

        Task t1 = new Task();
        t1.setUserToken(user1);
        t1.setDeleted(false);

        Task t2 = new Task();
        t2.setUserToken(user1);
        t2.setDeleted(true);

        Task t3 = new Task();
        t3.setUserToken(user2);
        t3.setDeleted(false);

        Task t4 = new Task();
        t4.setUserToken(user1);
        t4.setDeleted(false);

        when(kanbanRepo.findAll()).thenReturn(List.of(t1, t2, t3, t4));

        List<Task> result = kanbanService.getAllUserTasks(100);

        assertEquals(2, result.size());
        assertTrue(result.contains(t1));
        assertTrue(result.contains(t4));
    }

    // getTaskByIndex
    @Test
    void shouldReturnTaskIfExistsAndNotDeleted() {
        Task task = new Task();
        task.setDeleted(false);

        when(kanbanRepo.findById(1)).thenReturn(Optional.of(task));

        Task result = kanbanService.getTaskByIndex(1);

        assertNotNull(result);
    }

    @Test
    void shouldReturnNullIfTaskDeleted() {
        Task task = new Task();
        task.setDeleted(true);

        when(kanbanRepo.findById(1)).thenReturn(Optional.of(task));

        Task result = kanbanService.getTaskByIndex(1);

        assertNull(result);
    }

    @Test
    void shouldReturnNullIfTaskDoesNotExist() {
        when(kanbanRepo.findById(1)).thenReturn(Optional.empty());

        Task result = kanbanService.getTaskByIndex(1);

        assertNull(result);
    }

    // updateTask
    @Test
    void shouldUpdateTask() {
        Task task = new Task();

        when(kanbanRepo.save(task)).thenReturn(task);

        Task result = kanbanService.updateTask(task);

        assertEquals(task, result);
        verify(kanbanRepo).save(task);
    }

    // deleteTask (soft delete)
    @Test
    void shouldSoftDeleteTaskAndReturnMessage() {
        Task task = new Task();
        task.setIndex(5);

        String message = kanbanService.deleteTask(task);

        assertTrue(task.isDeleted());
        verify(kanbanRepo).save(task);
        assertTrue(message.contains("5"));
    }

    // addTask
    @Test
    void shouldAddTask() {
        Task task = new Task();

        when(kanbanRepo.save(task)).thenReturn(task);

        Task result = kanbanService.addTask(task);

        assertEquals(task, result);
    }

    // findByState
    @Test
    void shouldReturnTasksByStateUserAndNotDeleted() {
        User user = new User();
        user.setToken(100);

        State state1 = new State();
        state1.setId(1);

        State state2 = new State();
        state2.setId(2);

        Task t1 = new Task();
        t1.setUserToken(user);
        t1.setState(state1);
        t1.setDeleted(false);

        Task t2 = new Task();
        t2.setUserToken(user);
        t2.setState(state1);
        t2.setDeleted(true);

        Task t3 = new Task();
        t3.setUserToken(user);
        t3.setState(state2);
        t3.setDeleted(false);

        when(kanbanRepo.findAll()).thenReturn(List.of(t1, t2, t3));

        List<Task> result = kanbanService.findByState(1, 100);

        assertEquals(1, result.size());
        assertTrue(result.contains(t1));
    }
}