package start17.Memento.domain;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String user_id;
    private String password;
    private String nickname;

    public User() {
    }

    public User(String user_id, String password, String nickname) {
        this.user_id = user_id;
        this.password = password;
        this.nickname = nickname;
    }
}
