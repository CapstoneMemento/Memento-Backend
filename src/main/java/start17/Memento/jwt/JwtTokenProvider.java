package start17.Memento.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private String secretKey = "mementosecret";
    public static final long ACCESS_TOKEN_VALID_TIME =  1000L * 60 * 30; //30분
    public static final long REFRESH_TOKEN_VALID_TIME =  1000L * 60 * 60 * 24 * 7; //7일
    public static final long REISSUE_TOKEN_VALID_TIME =  1000L * 60 * 60 * 24 * 3; //7일

    //객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    //Access 토큰 생성
    public String createAccessToken(String username) {
        return doGenerateToken(username, ACCESS_TOKEN_VALID_TIME);
    }

    //Refresh 토큰 생성
    public String createRefreshToken(String username) {
        return doGenerateToken(username, REFRESH_TOKEN_VALID_TIME);
    }

    private String doGenerateToken(String username, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("username", username);
        Date now = new Date((System.currentTimeMillis()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //토큰에서 username 추출
    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    //토큰 만료 확인
    public Boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token, UserDetails userDetails) {
        String userid = getUsername(token);
        return userid.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    //토큰 만료까지 남은 시간 가져오기
    public long getRemainMilliSeconds(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        Date now = new Date();
        return expiration.getTime() - now.getTime();
    }

}
