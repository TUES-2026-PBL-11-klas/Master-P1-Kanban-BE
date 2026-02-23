package Master.Kanban.service;

import Master.Kanban.model.Task;
import Master.Kanban.repository.KanbanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KanbanServiceImplementation implements KanbanService {

    private final KanbanRepository kanbanRepo;

    public KanbanServiceImplementation(KanbanRepository kanbanRepo) {
        this.kanbanRepo = kanbanRepo;
    }

    @Override
    public List<Task> getAllUserTasks(long UsrAuthT) {
        return kanbanRepo
                .findAll()
                .stream()
                .filter(t -> t.getUsrAuthT() == UsrAuthT && !t.isDeleted())
                .toList();
    }

    @Override
    public Task getTaskByIndex(int index) {
        return kanbanRepo.findById(index).orElse(null);
    }

    @Override
    public Task updateTask(Task task) {
        return kanbanRepo.save(task);
    }

    @Override
    public String deleteTask(Task task) {
        task.setDeleted(true);
        kanbanRepo.save(task);
        // kanbanRepo.delete(task);
        return "Task deleted successfully with index: " + task.getIndex();
    }

    @Override
    public Task addTask(Task task) {
        return kanbanRepo.save(task);
    }

    @Override
    public List<Task> findByState(int state) {
        return List.of();
    }
}
