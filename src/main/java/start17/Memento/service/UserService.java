package start17.Memento.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start17.Memento.model.dao.UserMapper;
import start17.Memento.domain.User;
import start17.Memento.model.dto.UserDto;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        encryptPassword(user);
        userMapper.createUser(user);
    }

    public User loginCheck(UserDto.LoginInfo userDto) {
        User user = userMapper.getUserById(userDto.getUser_id());
        if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }

    private void encryptPassword(User user) {
        String enPw = passwordEncoder.encode(user.getPassword());
        user.setPassword(enPw);
    }


}
