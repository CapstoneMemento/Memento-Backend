package start17.Memento.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userid;
    private String password;
    private String nickname;

    @Getter @Setter
    public static class LoginInfo {
        private String userid;
        private String password;
    }
}
