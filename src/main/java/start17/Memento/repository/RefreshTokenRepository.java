package start17.Memento.repository;

import org.springframework.data.repository.CrudRepository;
import start17.Memento.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
