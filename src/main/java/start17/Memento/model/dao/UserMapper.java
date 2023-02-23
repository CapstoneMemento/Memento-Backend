package start17.Memento.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import start17.Memento.domain.User;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    void createUser(User user);
    User getUserById(String user_id);
    List<User> getAllUser();
    void updateUser(User user);
}
