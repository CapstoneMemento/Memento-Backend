package start17.Memento.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), GUEST("ROLE_NOT_PERMITTED");
    private String value;
}
