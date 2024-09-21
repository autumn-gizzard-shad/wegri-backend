package shad.wegri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shad.wegri.domain.Pin;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {
    @Query(value = "SELECT * FROM pin WHERE map_id = :mapId", nativeQuery = true)
    List<Pin> findByMapId(@Param("mapId") Long mapId);
}
