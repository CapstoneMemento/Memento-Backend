package start17.Memento.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start17.Memento.domain.RefreshToken;
import start17.Memento.domain.Role;
import start17.Memento.domain.UserEntity;
import start17.Memento.exception.CustomException;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.LoginResponseDto;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.model.dto.UserDto;
import start17.Memento.repository.RefreshTokenRepository;
import start17.Memento.repository.UserRepository;
import start17.Memento.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
 public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserEntity createUser(UserDto userDto) {
        //아이디 중복 검사
        UserEntity findUser = userRepository.findByUserid(userDto.getUserid());
        if (findUser != null) {
            throw new CustomException("이미 존재하는 아이디입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //저장
        UserEntity userEntity = new UserEntity();
        userEntity.setUserid(userDto.getUserid());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setNickname(userDto.getNickname());
        userEntity.setRoles(Collections.singletonList(Role.ROLE_USER)); //수정할 것
        return userRepository.save(userEntity);
    }

    @Override
    public LoginResponseDto login(UserDto.LoginInfo user) {
        String userid = user.getUserid();
        String password = user.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
            //토큰 생성
            String token = jwtTokenProvider.createToken(userid, userRepository.findByUserid(userid).getRoles());

            String nickname = userRepository.findByUserid(userid).getNickname();
            return new LoginResponseDto(userid, nickname, token);
        } catch (AuthenticationException e) {
            throw new CustomException("잘못된 아이디/비밀번호가 입력되었습니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
