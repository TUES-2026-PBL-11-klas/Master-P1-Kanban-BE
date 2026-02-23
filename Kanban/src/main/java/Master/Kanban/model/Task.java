package Master.Kanban.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private int index;
    private long UsrAuthT;
    private boolean deleted;
    private String title;
    private String desc;
    private int priority;
    private int state;
    private String tszImplement; //not implemented
}
