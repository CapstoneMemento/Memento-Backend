package start17.Memento.service;

import start17.Memento.entity.UserEntity;
import start17.Memento.model.dto.UserDto;

public interface UserService{

    UserEntity createUser(UserDto userDto);
}
