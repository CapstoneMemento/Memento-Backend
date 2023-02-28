package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.User;
import start17.Memento.model.dto.UserDto;
import start17.Memento.model.dto.UserRegisterDto;
import start17.Memento.service.UserService;
import start17.Memento.service.UserServiceImpl;

import java.io.IOException;
import java.util.Objects;

@Api(tags = "Users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    //private final UserServiceImpl userServiceImpl;
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto user) throws IOException {
        UserDto registerUser = userService.createUser(user);

        if(!Objects.isNull(registerUser)) {
            log.info("register success");
            return "redirect:/users/login";
        }
        log.info("register fail");
        return "redirect:/users/register";
    }

//    @ApiOperation(value = "로그인", notes = "회원 id, 비밀번호를 확인해 로그인한다.")
//    @PostMapping("/login")
//    public String login(@RequestBody UserDto.LoginInfo user) throws IOException {
//        User loginUser = userServiceImpl.loginCheck(user);
//        if(loginUser != null) {
//            log.info("login success");
//            return "Login success";
//        }
//        log.info("login fail");
//        return "redirect:/login";
//    }
}
