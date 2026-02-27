package Master.Kanban.repository;

import Master.Kanban.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndDeletedFalse(Long userId);
    List<Task> findByUserIdAndStateIdAndDeletedFalse(Long userId, Integer stateId);
}
