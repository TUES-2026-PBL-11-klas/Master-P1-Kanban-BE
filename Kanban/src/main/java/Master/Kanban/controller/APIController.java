package Master.Kanban.controller;

import Master.Kanban.model.Task;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class APIController {

    private final List<Task> tasks = List.of(
        new Task(
            1,
            101,
            false,
            "Fix API",
            "Implement the controller",
            1,
            0,
            "2026-02-20"
        ),
        new Task(
            2,
            102,
            false,
            "Write Docs",
            "Explain the uint logic",
            2,
            1,
            "2026-02-21"
        ),
        new Task(
            3,
            103,
            false,
            "Test API",
            "Verify 400 Bad Request works",
            3,
            0,
            "2026-02-22"
        )
    );

    @GetMapping
    public ResponseEntity<List<Task>> getTasksRange(
        @RequestParam int from,
        @RequestParam int to
    ) {
        if (from < 1 || to > tasks.size() || from > to) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Task> result = tasks
            .stream()
            .filter(t -> t.index() >= from && t.index() <= to)
            .toList();

        return ResponseEntity.ok(result);
    }
}
