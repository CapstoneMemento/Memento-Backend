package start17.Memento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import start17.Memento.model.dto.User;

import java.io.IOException;

@Api(tags = "Users")
@RestController
public class UserController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @ApiOperation(value = "회원가입", notes = "회원 id, 비밀번호, 닉네임을 입력해 user 정보를 생성한다.")
    @PostMapping("/users/register")
    public String register(@RequestBody User user) throws IOException {
        // 저장 로직 생성
        return "Register success";
    }
}
