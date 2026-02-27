package Master.Kanban;

import Master.Kanban.model.State;
import Master.Kanban.repository.StateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanApplication.class, args);
    }

    // Initialize state table
    @Bean
    CommandLineRunner initStates(StateRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new State(0, "Newly created", null));
                repo.save(new State(1, "Work in progress", null));
                repo.save(new State(2, "Finished", null));
            }
        };
    }
}
