package start17.Memento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start17.Memento.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String user_id);
}
