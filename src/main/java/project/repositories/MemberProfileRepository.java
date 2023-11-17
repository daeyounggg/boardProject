package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.MemberProfile;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {


}
