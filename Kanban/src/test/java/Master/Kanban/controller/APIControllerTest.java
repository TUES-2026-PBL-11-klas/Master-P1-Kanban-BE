package Master.Kanban.controller;

import Master.Kanban.model.Task;
import Master.Kanban.model.User;
import Master.Kanban.service.KanbanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(APIController.class)
class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KanbanService kanbanService;

    @Autowired
    private ObjectMapper objectMapper;

    // GET all user tasks
    @Test
    void shouldReturnAllTasksForUser() throws Exception {
        User user = new User();
        user.setToken(100);

        Task task = new Task();
        task.setIndex(1);
        task.setUserToken(user);

        when(kanbanService.getAllUserTasks(100))
                .thenReturn(List.of(task));

        mockMvc.perform(get("/api/v1/tasks/user/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].index").value(1));
    }

    // GET task by index
    @Test
    void shouldReturnTaskByIndex() throws Exception {
        Task task = new Task();
        task.setIndex(5);

        when(kanbanService.getTaskById(5)).thenReturn(task);

        mockMvc.perform(get("/api/v1/tasks/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index").value(5));
    }

    @Test
    void shouldReturn404IfTaskNotFound() throws Exception {
        when(kanbanService.getTaskById(5)).thenReturn(null);

        mockMvc.perform(get("/api/v1/tasks/5"))
                .andExpect(status().isNotFound());
    }

    // POST create task
    @Test
    void shouldCreateTask() throws Exception {
        Task task = new Task();
        task.setIndex(10);

        when(kanbanService.addTask(any(Task.class)))
                .thenReturn(task);

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index").value(10));
    }

    // PUT update task
    @Test
    void shouldUpdateTask() throws Exception {
        Task task = new Task();
        task.setIndex(7);

        when(kanbanService.updateTask(any(Task.class)))
                .thenReturn(task);

        mockMvc.perform(put("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index").value(7));
    }

    // DELETE task
    @Test
    void shouldDeleteTask() throws Exception {
        Task task = new Task();
        task.setIndex(3);

        when(kanbanService.getTaskById(3)).thenReturn(task);
        when(kanbanService.deleteTask(task))
                .thenReturn("deleted");

        mockMvc.perform(delete("/api/v1/tasks/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted"));
    }

    @Test
    void shouldReturn404WhenDeletingMissingTask() throws Exception {
        when(kanbanService.getTaskById(3)).thenReturn(null);

        mockMvc.perform(delete("/api/v1/tasks/3"))
                .andExpect(status().isNotFound());
    }

    // GET tasks by state
    @Test
    void shouldReturnTasksByState() throws Exception {
        Task task = new Task();
        task.setIndex(1);

        when(kanbanService.findByState(1, 100))
                .thenReturn(List.of(task));

        mockMvc.perform(get("/api/v1/tasks/user/100/state/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].index").value(1));
    }
}