package start17.Memento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.User;
import start17.Memento.model.dto.UserDto;
import start17.Memento.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api(tags = "Users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/users/register")
    public String register(@RequestBody User user) throws IOException {
        userService.createUser(user);
        log.info("register success");
        return "Register success";
    }

    @ApiOperation(value = "로그인", notes = "회원 id, 비밀번호를 입력해 로그인한다.")
    @PostMapping("/users/login")
    public String login(@RequestBody UserDto.LoginInfo user) throws IOException {

        User login_user = userService.loginCheck(user);

        if(login_user != null) {
            log.info("login success");
            return "Login success";
        }
        log.info("login fail");
        return "redirect:/users/login";
    }
}
