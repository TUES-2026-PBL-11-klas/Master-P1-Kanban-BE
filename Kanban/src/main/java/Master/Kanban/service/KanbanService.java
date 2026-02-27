package Master.Kanban.service;

import Master.Kanban.model.Task;
import Master.Kanban.model.User;

import java.util.List;
import java.util.Optional;

public interface KanbanService {
    // User
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);

    // Task
    List<Task> getAllUserTasks(Long userId);
    Optional<Task> getTaskById(Long taskId);
    Task updateTask(Task task);
    String deleteTask(Long taskId);
    Task addTask(Task task);
    List<Task> getUserTasksByState(Integer stateId, Long userId);
}
