package start17.Memento.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtHeaderUtilEnums {

    GRANT_TYPE("JWT 타입 / Bearer ", "Bearer ");

    private String description;
    private String value;
}
