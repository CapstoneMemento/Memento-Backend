package start17.Memento.service;

import start17.Memento.domain.UserEntity;
import start17.Memento.model.dto.LoginResponseDto;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.model.dto.UserDto;

import javax.servlet.http.HttpServletResponse;

public interface UserService{

    UserEntity createUser(UserDto userDto);
    LoginResponseDto login(UserDto.LoginInfo user);
}
