package project.repositories;

import org.springframework.data.repository.CrudRepository;
import project.entities.Member;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

   Optional<Member> findByEmail(String email);
}
