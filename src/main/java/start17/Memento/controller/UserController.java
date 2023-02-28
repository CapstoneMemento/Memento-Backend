package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import start17.Memento.entity.UserEntity;
import start17.Memento.exception.CustomException;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.UserDto;
import start17.Memento.repository.UserRepository;
import start17.Memento.service.UserService;

@Api(tags = "Users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) throws Exception {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @ApiOperation(value = "로그인", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
    @PostMapping("/login")
    public String login(@RequestBody UserDto.LoginInfo user) {
        String userid = user.getUserid();
        String password = user.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
            return jwtTokenProvider.createToken(userid, userRepository.findByUserid(userid).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("잘못된 아이디/비밀번호가 입력되었습니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
