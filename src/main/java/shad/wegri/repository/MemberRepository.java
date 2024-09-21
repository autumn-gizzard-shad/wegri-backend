package shad.wegri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shad.wegri.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
