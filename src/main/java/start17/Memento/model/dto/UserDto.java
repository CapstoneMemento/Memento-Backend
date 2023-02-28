package start17.Memento.model.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    private String user_id;
    private String password;

    @Getter @Setter
    public static class LoginInfo {
        private String user_id;
        private String password;
    }
}
