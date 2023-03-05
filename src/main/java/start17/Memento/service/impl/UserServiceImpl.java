package start17.Memento.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import start17.Memento.domain.CacheKey;
import start17.Memento.domain.LogoutAccessToken;
import start17.Memento.domain.RefreshToken;
import start17.Memento.domain.UserEntity;
import start17.Memento.exception.CustomException;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.LoginResponseDto;
import start17.Memento.model.dto.TokenDto;
import start17.Memento.model.dto.UserDto;
import start17.Memento.repository.LogoutAccessTokenRedisRepository;
import start17.Memento.repository.RefreshTokenRepository;
import start17.Memento.repository.UserRepository;
import start17.Memento.service.UserService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
 public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;

    //회원가입
    @Override
    public UserEntity createUser(UserEntity registerDto) {
        //아이디 중복 검사
        UserEntity findUser = userRepository.findByUserid(registerDto.getUserid()).get();
        log.info("findUser: {}", findUser);
        if (findUser != null) {
            throw new CustomException("이미 존재하는 아이디입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //저장
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return userRepository.save(UserEntity.ofUser(registerDto));
    }

    @Override
    public UserEntity createAdminUser(UserEntity registerDto) {
        //아이디 중복 검사
        UserEntity findUser = userRepository.findByUserid(registerDto.getUserid()).get();
        log.info("findUser: {}", findUser);
        if (findUser != null) {
            throw new CustomException("이미 존재하는 아이디입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //저장
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return userRepository.save(UserEntity.ofAdmin(registerDto));
    }

    //로그인
    @Override
    public LoginResponseDto login(UserDto.LoginInfo user) {
        String userid = user.getUserid();
        String password = user.getPassword();

        try {
            UserEntity userEntity = userRepository.findByUserid(user.getUserid())
                    .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
            checkPassword(password, userEntity.getPassword());
            //토큰 생성
            String username = userEntity.getUsername();
            String accessToken = jwtTokenProvider.createAccessToken(username);
            RefreshToken refreshToken = saveRefreshToken(username);
            String nickname = userEntity.getNickname();
            return new LoginResponseDto(userid, nickname, accessToken, refreshToken.getRefreshToken());
        } catch (AuthenticationException e) {
            throw new CustomException("잘못된 아이디/비밀번호가 입력되었습니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void checkPassword(String rawPassword, String findUserPassword) {
        if (!passwordEncoder.matches(rawPassword, findUserPassword)) {
            throw new IllegalArgumentException("잘못된 비밀번호가 입력되었습니다.");
        }
    }

    private RefreshToken saveRefreshToken(String username) {
        return refreshTokenRepository.save(RefreshToken.createRefreshToken(username,
                jwtTokenProvider.createRefreshToken(username), JwtTokenProvider.REFRESH_TOKEN_VALID_TIME));
    }

    //로그아웃
    @Override
    @CacheEvict(value = CacheKey.USER, key = "#username")
    public void logout(TokenDto tokenDto, String username) {
        String accessToken = resolveToken(tokenDto.getAccessToken());
        long remainMilliSeconds = jwtTokenProvider.getRemainMilliSeconds(accessToken);
        refreshTokenRepository.deleteById(username);
        logoutAccessTokenRedisRepository.save(LogoutAccessToken.of(accessToken, username, remainMilliSeconds));
    }

    public String resolveToken(String token) {
        return token.substring(7);
    }

    //토큰 재발행
    @Override
    public TokenDto reIssue(String refreshToken) {
        refreshToken = resolveToken(refreshToken);
        String username = getCurrentUsername();
        RefreshToken redishRefreshToken = refreshTokenRepository.findById(username).orElseThrow(NoSuchElementException::new);

        if (refreshToken.equals(redishRefreshToken.getRefreshToken())) {
            return reIssueRefreshToken(refreshToken, username);
        }
        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
    }

    private TokenDto reIssueRefreshToken(String refreshToken, String username) {
        if(jwtTokenProvider.getRemainMilliSeconds(refreshToken) < jwtTokenProvider.REISSUE_TOKEN_VALID_TIME) {
            String accessToken = jwtTokenProvider.createAccessToken(username);
            return TokenDto.of(accessToken, saveRefreshToken(username).getRefreshToken());
        }
        return TokenDto.of(jwtTokenProvider.createAccessToken(username), refreshToken);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

}
