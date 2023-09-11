package com.movte.slate.domain.user.application.service;

import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.jwt.application.usecase.IssueTokenUseCase;
import com.movte.slate.oidc.application.usecase.DecodeIdTokenUseCase;
import com.movte.slate.oidc.domain.IdToken;
import com.movte.slate.domain.user.application.port.FindUserByOauthIdAndOauthProviderPort;
import com.movte.slate.domain.user.application.port.SaveUserPort;
import com.movte.slate.domain.user.application.service.request.SignupServiceRequest;
import com.movte.slate.domain.user.application.service.response.SignupServiceResponse;
import com.movte.slate.domain.user.application.usecase.SignupUseCase;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.domain.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class SignupService implements SignupUseCase {
    private final DecodeIdTokenUseCase decodeIdTokenUseCase;
    private final FindUserByOauthIdAndOauthProviderPort findUserByOauthIdAndOauthProviderPort;
    private final SaveUserPort saveUserPort;
    private final IssueTokenUseCase issueTokenUseCase;

    @Override
    @Transactional
    public SignupServiceResponse signup(SignupServiceRequest request) {
        IdToken idToken = decodeIdTokenUseCase.decodeIdToken(request.getIdToken());
        String providerValue = request.getProvider();
        OauthProvider provider;
        try {
            provider = OauthProvider.valueOf(providerValue);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_AVAILIABLE_PROVIDER);
        }
        // 중복 회원 가입 방지
        Optional<User> userOpt = findUserByOauthIdAndOauthProviderPort.findByOauthIdAndOauthProvider(idToken.getOauthId(), provider);
        if (userOpt.isPresent()) {
            throw new BadRequestException(BadRequestExceptionCode.ALREADY_USER);
        }
        User user = User.builder()
                .oauthId(idToken.getOauthId())
                .userState(UserState.APPROVED)
                .oauthProvider(provider)
                .profileImageUrl(request.getProfileImageUrl())
                .nickname(idToken.getNickname())
                .build();
        user = saveUserPort.save(user);
        String accessToken = issueTokenUseCase.createAccessToken(user.getId());
        String refreshToken = issueTokenUseCase.createRefreshToken(user.getId());
        return new SignupServiceResponse(accessToken, refreshToken);
    }
}
