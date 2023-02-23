package start17.Memento.model.dto;

import lombok.Data;

public class UserDto {

    private String user_id;
    private String password;

    @Data
    public static class LoginInfo {
        private String user_id;
        private String password;
    }
}
