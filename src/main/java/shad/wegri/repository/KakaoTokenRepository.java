package shad.wegri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shad.wegri.domain.KakaoToken;

public interface KakaoTokenRepository extends JpaRepository<KakaoToken, String> {

}
