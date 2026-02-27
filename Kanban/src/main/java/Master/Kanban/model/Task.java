package Master.Kanban.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "\"Position\"", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Token\"", nullable = false)
    private User user;

    @Column(name = "\"Deleted\"", nullable = false)
    private boolean deleted;

    @Column(name = "\"Name\"")
    private String title;

    @Column(name = "\"Description\"")
    private String desc;

    @Column(name = "\"Priority\"", nullable = false)
    private int priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"State\"", nullable = false)
    private State state;

    @Column(name = "\"Time\"", nullable = false)
    private LocalDateTime createdAt;
}
