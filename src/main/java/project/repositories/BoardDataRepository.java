package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import project.entities.BoardData;

public interface BoardDataRepository extends JpaRepository<BoardData, Long>, QuerydslPredicateExecutor<BoardData> {
}
