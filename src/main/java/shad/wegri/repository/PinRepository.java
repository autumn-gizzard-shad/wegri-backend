package shad.wegri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import shad.wegri.domain.Pin;

public interface PinRepository extends JpaRepository<Pin, Long> {

}
