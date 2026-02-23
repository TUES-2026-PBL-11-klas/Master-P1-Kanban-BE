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
    public Task getTaskByIndex(long index) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }

    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public List<Task> findByState(int state) {
        return List.of();
    }
}
