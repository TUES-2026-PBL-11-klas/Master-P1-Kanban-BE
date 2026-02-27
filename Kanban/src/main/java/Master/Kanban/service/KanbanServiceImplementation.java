package Master.Kanban.service;

import Master.Kanban.model.State;
import Master.Kanban.model.Task;
import Master.Kanban.model.User;
import Master.Kanban.repository.StateRepository;
import Master.Kanban.repository.TaskRepository;
import Master.Kanban.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KanbanServiceImplementation implements KanbanService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final StateRepository stateRepo;

    public KanbanServiceImplementation(TaskRepository taskRepo, UserRepository userRepo, StateRepository stateRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.stateRepo = stateRepo;
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Task> getAllUserTasks(Long userId) {
        return taskRepo.findByUserIdAndDeletedFalse(userId);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        Optional<Task> t = taskRepo.findById(taskId);
        if (t.isPresent() && t.get().isDeleted()) {
            return Optional.empty();
        }
        return t;
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public String deleteTask(Long taskId) {
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if(taskOpt.isEmpty() || taskOpt.get().isDeleted()) {
            return "Task not found";
        }
        Task task = taskOpt.get();
        task.setDeleted(true);
        taskRepo.save(task);
        // taskRepository.delete(task);
        return "Task deleted successfully with id: " + task.getId();
    }

    @Override
    public Task addTask(Task task) {
        User user = userRepo.findById(task.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        State state = stateRepo.findById(task.getState().getId())
                .orElseThrow(() -> new RuntimeException("State not found"));

        task.setUser(user);
        task.setState(state);

        return taskRepo.save(task);
    }

    @Override
    public List<Task> getUserTasksByState(Integer stateId, Long userId) {
        return taskRepo.findByUserIdAndStateIdAndDeletedFalse(userId, stateId);
    }
}
