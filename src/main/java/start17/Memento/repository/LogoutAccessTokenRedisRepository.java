package start17.Memento.repository;

import org.springframework.data.repository.CrudRepository;
import start17.Memento.domain.account.LogoutAccessToken;

public interface LogoutAccessTokenRedisRepository extends CrudRepository<LogoutAccessToken, String> {
}
