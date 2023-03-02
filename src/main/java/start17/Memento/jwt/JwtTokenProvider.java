package start17.Memento.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import start17.Memento.service.impl.CustomUserDetailsService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private String secretKey = "mementosecret";
    public static final long ACCESS_TOKEN_VALID_TIME =  1000L * 60 * 30; //30분
    public static final long REFRESH_TOKEN_VALID_TIME =  1000L * 60 * 60 * 24 * 7; //7일
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";

    private final CustomUserDetailsService customUserDetailsService;

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
    public String createAccessToken(String userid) {
        return doGenerateToken(userid, ACCESS_TOKEN_VALID_TIME);
    }

    //Refresh 토큰 생성
    public String createRefreshToken(String userid) {
        return doGenerateToken(userid, REFRESH_TOKEN_VALID_TIME);
    }

    private String doGenerateToken(String userid, long expireTime) {
        Claims claims = Jwts.claims();
        Date now = new Date((System.currentTimeMillis()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        String userid = getUserid(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userid);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 userid 추출
    public String getUserid(String token) {
        return extractAllClaims(token).getSubject();
    }

    //토큰 만료 확인
    public Boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

//    // Request의 Header에서 token 값 가져오기. "Authorization" : "TOKEN값'
//    public String resolveToken(HttpServletRequest request) {
//        return request.getHeader("Authorization");
//    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token, UserDetails userDetails) {
        String userid = getUserid(token);
        return userid.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    public long getRemainMilliSeconds(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        Date now = new Date();
        return expiration.getTime() - now.getTime();
    }

}
