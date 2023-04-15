package start17.Memento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import start17.Memento.domain.account.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserid(String userid);
    Optional<UserEntity> findByUsername(String username);

    @Query("select u from UserEntity u join fetch u.authorities where u.username = :username")
    Optional<UserEntity> findByUsernameWithAuthority(@Param("username") String username);

}
