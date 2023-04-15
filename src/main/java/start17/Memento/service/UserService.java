package start17.Memento.service;

import start17.Memento.domain.account.UserEntity;
import start17.Memento.model.dto.LoginResponseDto;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.model.dto.UserDto;


public interface UserService{

    UserEntity createUser(UserEntity registerDto);
    UserEntity createAdminUser(UserEntity registerDto);
    LoginResponseDto login(UserDto.LoginInfo user);
    void logout(TokenDto tokenDto, String username);
    TokenDto reIssue(String refreshToken);
    void deleteUser(String userid, String username);
}
