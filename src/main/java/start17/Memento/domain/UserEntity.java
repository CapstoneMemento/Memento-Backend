package start17.Memento.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @Column(name = "USER_ID", unique = true)
    private String userid;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    public static UserEntity ofUser(UserEntity userEntity) {
        UserEntity user = UserEntity.builder()
                .username(UUID.randomUUID().toString())
                .userid(userEntity.getUserid())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .build();
        user.addAuthority(Authority.ofUser(user));
        return user;
    }

    public static UserEntity ofAdmin(UserEntity userEntity) {
        UserEntity user = UserEntity.builder()
                .username(UUID.randomUUID().toString())
                .userid(userEntity.getUserid())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .build();
        user.addAuthority(Authority.ofAdmin(user));
        return user;
    }

    private void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public List<String> getRoles() {
        return authorities.stream()
                .map(Authority::getRole)
                .collect(Collectors.toList());
    }
}
