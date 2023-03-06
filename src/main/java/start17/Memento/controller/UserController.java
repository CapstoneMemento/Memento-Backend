package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.domain.UserEntity;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.UserDto;
import start17.Memento.service.UserService;


@Api(tags = "Users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @ApiOperation(value = "관리자 회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 Admin user 정보를 생성한다.")
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.createAdminUser(user));
    }

    @ApiOperation(value = "로그인", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto.LoginInfo user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @ApiOperation(value = "로그아웃", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
    @DeleteMapping("/logout")
    public String logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenProvider.getUsername(resolveToken(accessToken));
        userService.logout(TokenDto.of(accessToken, refreshToken), username);
        return "Logout success";
    }

    @ApiOperation(value = "토큰 재발급", notes = "Refresh 토큰이 유효하지 않을 시 재발급한다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reIssue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok(userService.reIssue(refreshToken));
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원 id를 확인해 계정을 삭제한다.")
    @DeleteMapping("/{userid}")
    public String delete(@RequestHeader("Authorization") String accessToken,
                       @PathVariable("userid") String userid) {
        String username = jwtTokenProvider.getUsername(resolveToken(accessToken));
        userService.deleteUser(userid, username);
        return "Account delete success";
    }
    private String resolveToken(String accessToken) {
        return accessToken.substring(7);
    }
}
