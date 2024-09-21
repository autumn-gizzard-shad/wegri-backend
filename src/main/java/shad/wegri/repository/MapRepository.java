package shad.wegri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shad.wegri.domain.Map;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {

    @Query(value = "SELECT m.*, COUNT(p.id) AS pin_count " +
                    "FROM map m " +
                    "LEFT JOIN pin p ON m.id = p.map_id " +
                    "GROUP BY m.id", nativeQuery = true)
    List<Object[]> findAllMapsWithPinCount();

}
