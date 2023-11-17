package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.HashTag;

public interface HashTagRepository extends JpaRepository<HashTag, String> {


}
