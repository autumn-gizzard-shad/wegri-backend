package shad.wegri.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shad.wegri.domain.Pin;
import shad.wegri.dto.BicycleSearchDto;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {
    @Query(value = "SELECT * FROM pin WHERE map_id = :mapId", nativeQuery = true)
    List<Pin> findByMapId(@Param("mapId") Long mapId);

    @Query(value = "SELECT id, date, latitude, longitude, image, provider, is_rent  FROM pin WHERE map_id = :bicycleMapId", nativeQuery = true)
    List<BicycleSearchDto> findByBicycleMapId(@Param("bicycleMapId") Long bicycleId);

    @Query(value = "SELECT is_rent from pin where id = :pinId and map_id = :mapId", nativeQuery = true)
    byte findByPinIdAndMapId(@Param("pinId") Long pinId, @Param("mapId") Long mapId);

    @Modifying
    @Transactional
    @Query("UPDATE Pin p SET p.is_rent = :is_rent WHERE p.map_id = :map_id AND p.id = :pin_id")
    void updateIsRentByMapIdAndPinId(@Param("is_rent") boolean isRent,
        @Param("map_id") Long mapId,
        @Param("pin_id") Long pinId);
}
