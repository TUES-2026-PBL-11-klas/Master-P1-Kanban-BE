package Master.Kanban.service;

import Master.Kanban.model.Task;

import java.util.List;

public interface KanbanService {
    List<Task> getAllUserTasks(long UsrAuthT);
    Task getTaskByIndex(int index);
    Task updateTask(Task task);
    String deleteTask(Task task);
    Task addTask(Task task);
    List<Task> findByState(int state);
}
