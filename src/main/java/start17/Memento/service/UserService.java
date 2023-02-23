package start17.Memento.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start17.Memento.model.dao.UserMapper;
import start17.Memento.domain.User;
import start17.Memento.model.dto.UserDto;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public User loginCheck(UserDto.LoginInfo user_dto) {

        User user = userMapper.getUserById(user_dto.getUser_id());

        if(user_dto.getPassword().equals(user.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }

}
