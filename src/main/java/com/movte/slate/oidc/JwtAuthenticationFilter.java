package com.movte.slate.oidc;

import com.movte.slate.domain.user.domain.UserState;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION = "Authorization";
    private final RedisTemplate<String, String> redisTemplate;
    private final TokenStringExtractor tokenStringExtractor;
    private final JwtTokenFactory jwtTokenFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath(); // 프로젝트 아래 경로만 가져옴
        if ( (path != null && path.startsWith("/oidc/kakao")) || (path != null && path.startsWith("/user/reissue")) || (path != null && path.startsWith("/user/pending"))) { // 카카오 OIDC 리다이렉트 URI는 필터 적용 제외
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String tokenString = tokenStringExtractor.extractTokenString(authorizationHeader);
        JwtToken jwtToken = jwtTokenFactory.create(tokenString);

        if (jwtToken.isExpired(new Date())) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.TOKEN_EXPIRED);
        }

        // redis에 access token이 있다는 것은 유효기간은 남았지만 로그아웃된 토큰이라는 것
        if (jwtToken.isAccessToken() && isTokenInRedis(tokenString) != null) {
            log.info("로그아웃된 토큰입니다.");
            throw new UnauthorizedException(UnauthorizedExceptionCode.LOGOUT_TOKEN);
        }

        // Token에서 UserId 꺼내기
        Long userId = jwtToken.getUserId();
        UserState userState = jwtToken.getUserState();
        // 아직 회원 추가 정보가 입력되지 않은 경우,
        if (UserState.PENDING.equals(userState)) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_ENOUGH_INFO);
        }

        mustNotReceiveAccessTokenWhenPathIsAccessTokenReissurancePath(path, jwtToken);
        mustNotReceiveRefreshTokenWhenPathIsNotAccessTokenReissurancePath(path, jwtToken);

        // SecurityContext 안에 Authentication 객체가 존재하는지의 유무를 체크해서 인증여부를 결정
        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail을 넣어줌
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Spring context에 저장
        log.info("[+] Token in SecurityContextHolder");

        // 다음 필터 단계로 넘어감
        filterChain.doFilter(request, response);
    }

    // Access Token 재발급 요청 api 인데, access token을 전달했을 경우인지 체크
    private void mustNotReceiveRefreshTokenWhenPathIsNotAccessTokenReissurancePath(String path, JwtToken jwtToken) {
        if (!doesUserRequestReassuranceOfAccessToken(path) && jwtToken.isRefreshToken()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_ACCESS_TOKEN);
        }
    }

    // 재발급 요청이 아닌데 refresh token을 전달했을 경우인지 체크
    private void mustNotReceiveAccessTokenWhenPathIsAccessTokenReissurancePath(String path, JwtToken jwtToken) {
        if (doesUserRequestReassuranceOfAccessToken(path) && jwtToken.isAccessToken()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_REFRESH_TOKEN);
        }
    }

    private boolean doesUserRequestReassuranceOfAccessToken(String path) {
        return path.startsWith("/token");
    }

    private String isTokenInRedis(String tokenString) {
        return redisTemplate.opsForValue().get(tokenString);
    }
}
