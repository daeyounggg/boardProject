package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

   Optional<Member> findByEmail(String email);
}
