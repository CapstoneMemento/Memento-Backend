package start17.Memento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start17.Memento.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserid(String userid);
}
