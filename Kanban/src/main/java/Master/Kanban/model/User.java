package Master.Kanban.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Token", nullable = false)
    private Map<String, > token;
//     Map<Integer, AnotherEntity> correctRelationship;

    @Column(name = "Username")
    private String username;
}
