package shad.wegri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shad.wegri.domain.Map;
import shad.wegri.domain.Pin;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    List<Pin> findByMap(Map map);
}
