package start17.Memento.service;

import org.springframework.transaction.annotation.Transactional;
import start17.Memento.domain.UserEntity;
import start17.Memento.model.dto.LoginResponseDto;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.model.dto.UserDto;

@Transactional
public interface UserService{

    UserEntity createUser(UserEntity registerDto);
    UserEntity createAdminUser(UserEntity registerDto);
    LoginResponseDto login(UserDto.LoginInfo user);
    void logout(TokenDto tokenDto, String userid);
//    TokenDto reIssue(TokenDto tokenDto);
}