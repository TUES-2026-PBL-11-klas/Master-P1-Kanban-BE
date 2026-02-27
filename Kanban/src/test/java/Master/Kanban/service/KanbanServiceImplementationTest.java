package Master.Kanban.service;

import Master.Kanban.model.Task;
import Master.Kanban.model.State;
import Master.Kanban.model.User;
import Master.Kanban.repository.StateRepository;
import Master.Kanban.repository.TaskRepository;
import Master.Kanban.repository.UserRepository;
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
    private TaskRepository taskRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private StateRepository stateRepo;

    @InjectMocks
    private KanbanServiceImplementation kanbanService;

    // getAllUserTasks
    @Test
    void shouldReturnOnlyTasksForUser() {
        User user1 = new User();
        user1.setId(100L);

        User user2 = new User();
        user2.setId(200L);

        Task t1 = new Task();
        t1.setUser(user1);
        t1.setDeleted(false);

//        Task t2 = new Task();
//        t2.setUser(user1);
//        t2.setDeleted(true);
//
//        Task t3 = new Task();
//        t3.setUser(user2);
//        t3.setDeleted(false);

        Task t4 = new Task();
        t4.setUser(user1);
        t4.setDeleted(false);

        when(taskRepo.findByUserIdAndDeletedFalse(100L)).thenReturn(List.of(t1, t4));

        List<Task> result = kanbanService.getAllUserTasks(100L);

        assertEquals(2, result.size());
        assertTrue(result.contains(t1));
        assertTrue(result.contains(t4));
    }

    // getTaskByIndex
    @Test
    void shouldReturnOptionalTaskIfExistsAndNotDeleted() {
        Task task = new Task();
        task.setDeleted(false);

        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = kanbanService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalIfTaskDeleted() {
        Task task = new Task();
        task.setDeleted(true);

        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = kanbanService.getTaskById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalIfTaskDoesNotExist() {
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        Optional<Task> result = kanbanService.getTaskById(1L);

        assertTrue(result.isEmpty());
    }

    // updateTask
    @Test
    void shouldUpdateTask() {
        Task task = new Task();

        when(taskRepo.save(task)).thenReturn(task);

        Task result = kanbanService.updateTask(task);

        assertEquals(task, result);
        verify(taskRepo).save(task);
    }

    // deleteTask (soft delete)
    @Test
    void shouldSoftDeleteTaskAndReturnMessage() {
        Task task = new Task();
        task.setId(5L);
        task.setDeleted(false);

        when(taskRepo.findById(5L)).thenReturn(Optional.of(task));
        when(taskRepo.save(task)).thenReturn(task);

        String message = kanbanService.deleteTask(5L);

        assertTrue(task.isDeleted());
        verify(taskRepo).save(task);
        assertTrue(message.contains("5"));
    }

    // addTask
    @Test
    void shouldAddTask() {
        User user = new User();
        user.setId(1L);

        State state = new State();
        state.setId(1);

        Task task = new Task();
        task.setUser(user);
        task.setState(state);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(stateRepo.findById(1)).thenReturn(Optional.of(state));
        when(taskRepo.save(task)).thenReturn(task);

        Task result = kanbanService.addTask(task);

        assertEquals(task, result);
        verify(taskRepo).save(task);
    }

    // findByState
    @Test
    void shouldReturnTasksByStateForUser() {
        User user = new User();
        user.setId(100L);

        State state1 = new State();
        state1.setId(1);

        State state2 = new State();
        state2.setId(2);

        Task t1 = new Task();
        t1.setUser(user);
        t1.setState(state1);
        t1.setDeleted(false);

//        Task t2 = new Task();
//        t2.setUser(user);
//        t2.setState(state1);
//        t2.setDeleted(true);
//
//        Task t3 = new Task();
//        t3.setUser(user);
//        t3.setState(state2);
//        t3.setDeleted(false);

        when(taskRepo.findByUserIdAndStateIdAndDeletedFalse(100L, 1)).thenReturn(List.of(t1));

        List<Task> result = kanbanService.getUserTasksByState(1, 100L);

        assertEquals(1, result.size());
        assertTrue(result.contains(t1));
    }

    // createUser
    @Test
    void shouldCreateUser() {
        User user = new User();
        when(userRepo.save(user)).thenReturn(user);

        User result = kanbanService.createUser(user);

        assertEquals(user, result);
        verify(userRepo).save(user);
    }

    // getUserById
    @Test
    void shouldReturnUserById() {
        User user = new User();
        user.setId(10L);

        when(userRepo.findById(10L)).thenReturn(Optional.of(user));

        Optional<User> result = kanbanService.getUserById(10L);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void shouldReturnEmptyIfUserNotFound() {
        when(userRepo.findById(10L)).thenReturn(Optional.empty());

        Optional<User> result = kanbanService.getUserById(10L);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnUserByUsername() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> result = kanbanService.getUserByUsername("testuser");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
}