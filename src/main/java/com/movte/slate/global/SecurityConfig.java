package com.movte.slate.global;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 *   머지가 안돼서 임의로 설정 파일 넣었습니다
 */
//@Configuration
public class SecurityConfig {

    //    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeRequests(requests -> {
                    requests.requestMatchers(new AntPathRequestMatcher("/"))
                            .authenticated(); // 그 외에는 인증된 사용자만 허락
                    requests.requestMatchers(new AntPathRequestMatcher("/oidc/kakao"))
                            .permitAll(); // 로그인 경로는 모든 사용자에게 허락
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 쓸 때 사용
                )
        ;
        return http.build();
    }
}
