package start17.Memento.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import start17.Memento.domain.UserEntity;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    void createUser(UserEntity userEntity);
    UserEntity getUserById(String user_id);
    List<UserEntity> getAllUser();
    void updateUser(UserEntity userEntity);
}
