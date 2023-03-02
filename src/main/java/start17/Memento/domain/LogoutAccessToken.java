package start17.Memento.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;

@Getter
@RedisHash("logoutAccessToken")
@AllArgsConstructor
@Builder
public class LogoutAccessToken {

    @Id
    private String id;

    private String userid;

    @TimeToLive
    private Long expiration;

    public static LogoutAccessToken of(String accessToken, String userid, Long remainingMilliSeconds) {
        return LogoutAccessToken.builder()
                .id(accessToken)
                .userid(userid)
                .expiration(remainingMilliSeconds / 1000)
                .build();
    }
}
