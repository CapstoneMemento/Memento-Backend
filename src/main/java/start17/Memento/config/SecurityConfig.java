package start17.Memento.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import start17.Memento.config.filter.JwtTokenFilter;
import start17.Memento.jwt.JwtAccessDeniedHandler;
import start17.Memento.jwt.JwtAuthenticationEntryPoint;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.repository.LogoutAccessTokenRedisRepository;
import start17.Memento.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;
    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public void configure(WebSecurity web) { // 4
//        web.ignoring().antMatchers("/swagger-ui.html/**");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors();

        //csrf 사용 X
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/users/register",
                        "/users/register/admin",
                        "/users/login"
                ).permitAll()
                .anyRequest().authenticated();

        //커스텀 로그인 뷰 사용
        http
                .formLogin().disable();

        //JWT 기반 로그아웃
        http
                .logout().disable();

        //세션 생성 및 사용 X
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        //JWT 예외 처리
        http
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        //JWT 적용
        http
                .apply(new JwtSecurityConfig(jwtTokenProvider, customUserDetailsService, logoutAccessTokenRedisRepository));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
