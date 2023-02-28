package start17.Memento.model.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    @NotNull
    private String user_id;

    @NotNull
    private String password;
}
