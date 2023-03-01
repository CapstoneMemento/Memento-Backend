package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> login(@RequestBody UserDto.LoginInfo user) {
        return ResponseEntity.ok(userService.login(user));
    }


}
