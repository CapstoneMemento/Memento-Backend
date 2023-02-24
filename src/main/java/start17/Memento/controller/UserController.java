package start17.Memento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import start17.Memento.domain.User;
import start17.Memento.service.UserService;

import java.io.IOException;

@Api(tags = "Users")
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/register")
    public String register(@RequestBody User user) throws IOException {
        userService.createUser(user);
        log.info("register success");
        return "Register success";
    }

}
