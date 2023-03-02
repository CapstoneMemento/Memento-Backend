package start17.Memento.service;

import start17.Memento.domain.UserEntity;
import start17.Memento.model.dto.LoginResponseDto;
import start17.Memento.model.dto.UserDto;


public interface UserService{

    UserEntity createUser(UserEntity registerDto);
    UserEntity createAdminUser(UserEntity registerDto);
    LoginResponseDto login(UserDto.LoginInfo user);
//    TokenDto reIssue(TokenDto tokenDto);
}
