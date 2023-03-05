package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.util.CookieUtil;
import start17.Memento.util.RedisUtil;
import start17.Memento.domain.UserEntity;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.UserDto;
import start17.Memento.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "Users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) throws Exception {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @ApiOperation(value = "관리자 회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 Admin user 정보를 생성한다.")
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody UserEntity user) throws Exception {
        return ResponseEntity.ok(userService.createAdminUser(user));
    }

    @ApiOperation(value = "로그인", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto.LoginInfo user,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        return ResponseEntity.ok(userService.login(user));
    }

    @ApiOperation(value = "로그아웃", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
    @DeleteMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenProvider.getUsername(resolveToken(accessToken));
        log.info("username: {}", username);
        userService.logout(TokenDto.of(accessToken, refreshToken), username);
    }

    private String resolveToken(String accessToken) {
        return accessToken.substring(7);
    }

    @ApiOperation(value = "토큰 재발급", notes = "Refresh 토큰이 유효하지 않을 시 재발급한다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reIssue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok(userService.reIssue(refreshToken));
    }


}
