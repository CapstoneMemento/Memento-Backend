package start17.Memento.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String userid;
    private String nickname;
    private String token;

    public LoginResponseDto(String userid, String nickname, String token) {
        this.userid = userid;
        this.nickname = nickname;
        this.token = token;
    }
}
