package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Configs;

public interface ConfigsRepository extends JpaRepository<Configs, String> {
}
