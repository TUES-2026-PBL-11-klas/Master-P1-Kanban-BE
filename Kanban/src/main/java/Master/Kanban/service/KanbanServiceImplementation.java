package Master.Kanban.service;

import Master.Kanban.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KanbanServiceImplementation implements KanbanService {

    @Override
    public List<Task> getAllUserTasks(long UsrAuthT) {
        return List.of();
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
