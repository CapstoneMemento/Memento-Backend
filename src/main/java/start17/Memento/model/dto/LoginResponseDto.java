package start17.Memento.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String userid;
    private String nickname;
    private String accessToken;
    private String refreshToken;

    public LoginResponseDto(String userid, String nickname, String accessToken, String refreshToken) {
        this.userid = userid;
        this.nickname = nickname;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
