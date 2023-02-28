package start17.Memento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.User;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.UserDto;
import start17.Memento.service.UserService;

import java.io.IOException;

@Api(tags = "Users")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    //회원가입(POST)
    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/register")
    public String register(@RequestBody User user) throws IOException {
        userService.createUser(user);
        log.info("register success");
        return "Register success";
    }

    //로그인(POST)
    @ApiOperation(value = "로그인", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
    @PostMapping("/login")
    public String login(@RequestBody UserDto.LoginInfo user) throws IOException {
        User loginUser = userService.loginCheck(user);
        if(loginUser != null) {
            log.info("login success");
            return jwtTokenProvider.createToken(loginUser.getUser_id(), loginUser.getRoles());
        }
        log.info("login fail");
        return "redirect:/login";
    }
}
