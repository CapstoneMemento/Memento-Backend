package start17.Memento.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start17.Memento.domain.Role;

@Getter @Setter
@NoArgsConstructor
public class UserDto {
    private String user_id;
    private String password;
    private String nickname;
    private Role role;
//
//    public static class LoginInfo {
//        private String user_id;
//        private String password;
//    }

    @Builder
    public UserDto(String user_id, String password, String nickname, Role role) {
        this.user_id = user_id;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
