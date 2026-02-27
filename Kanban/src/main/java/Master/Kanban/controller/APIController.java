package Master.Kanban.controller;

import Master.Kanban.model.Task;
import Master.Kanban.model.User;
import Master.Kanban.service.KanbanService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class APIController {

    private final KanbanService kanbanService;

    public APIController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @GetMapping("/tasks/user/{userId}")
    public List<Task> getAllTasks(@PathVariable Long userId) {
        return kanbanService.getAllUserTasks(userId);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        return kanbanService.getTaskById(taskId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if(task.getUser() == null || task.getState() == null) {
            return ResponseEntity.badRequest().build();
        }
        Task savedTask = kanbanService.addTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/tasks")
    public Task updateTask(@RequestBody Task task) {
        return kanbanService.updateTask(task);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        String message = kanbanService.deleteTask(taskId);
        if(message.equals("Task not found")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/tasks/user/{userId}/state/{stateId}")
    public List<Task> getTasksByState(
            @PathVariable Long userId,
            @PathVariable Integer stateId
    ) {
        return kanbanService.getUserTasksByState(stateId, userId);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return kanbanService.createUser(user);
    }
}
