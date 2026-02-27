package Master.Kanban.model;

import jakarta.persistence.*;
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
    @Column(name = "\"Position\"", nullable = false)
    private int index;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"Token\"", nullable = false)
    private User userToken;

    @Column(name = "\"Deleted\"", nullable = false)
    private boolean deleted;

    @Column(name = "\"Name\"")
    private String title;

    @Column(name = "\"Description\"")
    private String desc;

    @Column(name = "\"Priority\"", nullable = false)
    private int priority;

    @ManyToOne(fetch = FetchType.EAGER) //probably best to ask if this is the best decisicion
    @JoinColumn(name = "\"State\"", nullable = false)
    private State state;

    @Column(name = "\"Time\"", nullable = false)
    private String tszImplement; //not implemented
}
