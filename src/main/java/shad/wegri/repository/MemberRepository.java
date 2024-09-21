package shad.wegri.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import shad.wegri.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(String id);

    boolean existsById(String id);
}
