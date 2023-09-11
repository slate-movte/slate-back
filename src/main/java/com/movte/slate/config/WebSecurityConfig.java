package com.movte.slate.config;

import com.movte.slate.jwt.JwtAuthenticationFilter;
import com.movte.slate.jwt.JwtExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity // 현재 클래스를 스프링 필터체인에 등록
@Log4j2
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtExceptionHandlerFilter jwtExceptionHandlerFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // 인가가 필요한 리소스 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeRequests(requests -> {
                    requests.antMatchers(HttpMethod.GET, "/user/info").authenticated();
                    requests.antMatchers(HttpMethod.PATCH, "/user/info").authenticated();
                    requests.antMatchers("/snapshot*").authenticated();
                    requests.requestMatchers(new AntPathRequestMatcher("/*")).permitAll(); // 로그인 경로는 모든 사용자에게 허락
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 쓸 때 사용
                )
                .addFilterBefore(jwtExceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class) // ExceptionHandler 필터가 앞에 와야 함!
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // UsernamePasswordAuthenticationFilter 앞에 JwtFilter 추가
        ;
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // cross-origin 요청이 허가되는 url
        configuration.setAllowedOrigins(List.of("*"));
        //허용할 헤더 설정
        configuration.addAllowedHeader("*");
        //허용할 http method
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
