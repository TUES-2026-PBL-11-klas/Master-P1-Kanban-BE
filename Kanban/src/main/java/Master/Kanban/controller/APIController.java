package Master.Kanban.controller;

import Master.Kanban.model.Task;
import Master.Kanban.service.KanbanService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class APIController {

    private final KanbanService kanbanService;

    public APIController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @GetMapping("/user/{usrAuthT}")
    public List<Task> getAllTasks(@PathVariable long usrAuthT) {
        return kanbanService.getAllUserTasks(usrAuthT);
    }

    @GetMapping("/{index}")
    public ResponseEntity<Task> getTask(@PathVariable int index) {
        Task task = kanbanService.getTaskByIndex(index);
        return task != null
            ? ResponseEntity.ok(task)
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return kanbanService.addTask(task);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        return kanbanService.updateTask(task);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteTask(@PathVariable int index) {
        Task task = kanbanService.getTaskByIndex(index);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        String message = kanbanService.deleteTask(task);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/state/{state}/user/{usrAuthT}")
    public List<Task> getTasksByState(
        @PathVariable int state,
        @PathVariable long usrAuthT
    ) {
        return kanbanService.findByState(state, usrAuthT);
    }
}
