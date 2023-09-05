package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.request.AccessTokenRefreshServiceRequest;
import com.movte.slate.domain.user.application.service.dto.request.LoginServiceRequest;
import com.movte.slate.domain.user.application.service.dto.request.SignupServiceRequest;
import com.movte.slate.domain.user.application.service.dto.response.AccessTokenRefreshResponse;
import com.movte.slate.domain.user.application.service.dto.response.LoginResponse;
import com.movte.slate.domain.user.application.service.dto.response.SignupResponse;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.domain.UserState;
import com.movte.slate.domain.user.repository.UserRepository;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.jwt.JwtTokenFactory;
import com.movte.slate.jwt.JwtTokenIssuer;
import com.movte.slate.jwt.domain.JwtToken;
import com.movte.slate.oidc.IdTokenDecoder;
import com.movte.slate.oidc.KakaoConfigProperties;
import com.movte.slate.oidc.domain.IdToken;
import com.movte.slate.oidc.dto.OidcPublicKeysDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final KakaoConfigProperties kakaoConfigProperties;
    private final UserRepository userRepository;
    private final GetKakaoPublicKeyAdapter getKakaoPublicKeyAdapter;
    private final JwtTokenIssuer jwtTokenIssuer;
    private final JwtTokenFactory jwtTokenFactory;

    /**
     * 로그인
     *
     * @param request 로그인 요청 (Id token)
     * @return 로그인 결과물 (Access Token, Refresh Token, 회원가입 여부)
     */
    public LoginResponse login(LoginServiceRequest request) {
        String idTokenValue = request.getIdToken();
        String providerValue = request.getProvider();
        OauthProvider provider;
        try {
            provider = OauthProvider.valueOf(providerValue);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_AVAILIABLE_PROVIDER);
        }

        IdToken idToken = decodeIdToken(idTokenValue);
        Optional<User> userOpt = userRepository.findByOauthIdAndOauthProvider(idToken.getOauthId(), provider);
        if (userOpt.isEmpty()) {
            return new LoginResponse(true, null, null);
        }
        User user = userOpt.get();
        String accessToken = jwtTokenIssuer.createAccessToken(user);
        String refreshToken = jwtTokenIssuer.createRefreshToken(user.getId());
        return new LoginResponse(true, accessToken, refreshToken);
    }

    /**
     * 회원 가입
     *
     * @param request 회원 가입을 위해 필요한 정보 (id token, nickname, profile image url)
     * @return 토큰들
     */
    public SignupResponse signupUser(SignupServiceRequest request) {
        IdToken idToken = decodeIdToken(request.getIdToken());
        String providerValue = request.getProvider();
        OauthProvider provider;
        try {
            provider = OauthProvider.valueOf(providerValue);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_AVAILIABLE_PROVIDER);
        }
        // 중복 회원 가입 방지
        Optional<User> userOpt = userRepository.findByOauthIdAndOauthProvider(idToken.getOauthId(), provider);
        if (userOpt.isPresent()) {
            throw new BadRequestException(BadRequestExceptionCode.ALREADY_USER);
        }
        User user = User.builder()
                .oauthId(idToken.getOauthId())
                .userState(UserState.APPROVED)
                .oauthProvider(provider)
                .profileImageUrl(request.getProfileImageUrl())
                .build();
        user = userRepository.save(user);
        String accessToken = jwtTokenIssuer.createAccessToken(user);
        String refreshToken = jwtTokenIssuer.createRefreshToken(user.getId());
        return new SignupResponse(accessToken, refreshToken);
    }

    /**
     * 액세스 토큰 재발급
     *
     * @param request 액세스 토큰을 재발급하기 위해 필요한 refresh token
     * @return 새로운 액세스 토큰
     */
    public AccessTokenRefreshResponse refreshAccessToken(AccessTokenRefreshServiceRequest request) {
        JwtToken refreshToken = jwtTokenFactory.create(request.getRefreshTokenValue());
        Long userId = refreshToken.getUserId();
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        String accessToken = jwtTokenIssuer.createAccessToken(user);
        return new AccessTokenRefreshResponse(accessToken);
    }


    /**
     * 로그아웃
     *
     * @param accessToken 액세스 토큰
     */
    public void logout(JwtToken accessToken) {
        Long userId = accessToken.getUserId();
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        // 로그아웃을 위한 별도 로직 없음
    }

    /**
     * 회원 탈퇴
     *
     * @param accessToken 액세스 토큰
     */
    public void withdrawal(JwtToken accessToken) {
        Long userId = accessToken.getUserId();
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_USER);
        }
        userRepository.deleteById(userId);
    }

    private IdToken decodeIdToken(String idToken) {
        String iss = kakaoConfigProperties.getIss();
        String restApiKey = kakaoConfigProperties.getRestApiKey();
        IdTokenDecoder idTokenDecoder = new IdTokenDecoder(iss, restApiKey);
        OidcPublicKeysDto publicKeys = getKakaoPublicKeyAdapter.getPublicKeys();
        return idTokenDecoder.decode(publicKeys, idToken);
    }

    public boolean checkIfNicknameIsDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }


}
