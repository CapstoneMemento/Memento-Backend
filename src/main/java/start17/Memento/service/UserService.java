package start17.Memento.service;

import start17.Memento.model.dto.UserDto;
import start17.Memento.model.dto.UserRegisterDto;

public interface UserService {
    UserDto createUser(UserRegisterDto userRegisterDto);
}
