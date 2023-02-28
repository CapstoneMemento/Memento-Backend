package start17.Memento.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(unique = true)
    private String userid;

    @Column
    private String password;

    @Column
    private String nickname;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;
}
