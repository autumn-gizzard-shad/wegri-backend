package shad.wegri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import shad.wegri.domain.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, String> {

    List<Favorite> findByMemberId(String memberId);
}
