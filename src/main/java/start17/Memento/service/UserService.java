package start17.Memento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start17.Memento.model.dao.UserMapper;
import start17.Memento.model.dto.User;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createUser(User user) {
        userMapper.createUser(user);
    }

}
