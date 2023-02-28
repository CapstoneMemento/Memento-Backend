package start17.Memento.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String user_id;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String user_id, String password, String nickname) {
        this.user_id = user_id;
        this.password = password;
        this.nickname = nickname;
    }
}
