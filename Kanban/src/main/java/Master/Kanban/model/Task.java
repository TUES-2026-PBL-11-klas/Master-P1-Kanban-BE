package Master.Kanban.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "Position", nullable = false)
    private int index;
    @Column(name = "Token", nullable = false)
    private long UsrAuthT;
    @Column(name = "Deleted", nullable = false)
    private boolean deleted;
    @Column(name = "Name")
    private String title;
    @Column(name = "Description")
    private String desc;
    @Column(name = "Priority", nullable = false)
    private int priority;
    @Column(name = "State", nullable = false)
    private int state;
    @Column(name = "Time", nullable = false)
    private String tszImplement; //not implemented
}
