package Master.Kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "states")
public class State {

    @Id
    @Column(name = "\"Id\"", nullable = false)
    private Integer id;

    @Column(name = "\"Name\"", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private List<Task> tasks;
}
