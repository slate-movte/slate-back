package com.movte.slate.domain.user.application.service;


import com.movte.slate.domain.user.application.service.dto.response.LoginResponse;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.UserRepository;
import com.movte.slate.jwt.JwtTokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginForDevelopUseCase {

    private final UserRepository userRepository;
    private final JwtTokenIssuer jwtTokenIssuer;

    @Transactional(readOnly = true)
    public LoginResponse login(String oauthId) {
        User user = findUser(oauthId);
        String accessToken = jwtTokenIssuer.createAccessToken(user);
        String refreshToken = jwtTokenIssuer.createRefreshToken(user.getId());
        return new LoginResponse(false, accessToken, refreshToken);
    }

    private User findUser(String oauthId) {
        return userRepository.findByOauthIdAndOauthProvider(oauthId, OauthProvider.KAKAO)
            .orElseThrow(IllegalArgumentException::new);
    }
}
