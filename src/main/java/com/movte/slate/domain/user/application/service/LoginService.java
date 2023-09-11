package com.movte.slate.domain.user.application.service;

import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.jwt.application.usecase.IssueTokenUseCase;
import com.movte.slate.oidc.application.usecase.DecodeIdTokenUseCase;
import com.movte.slate.oidc.domain.IdToken;
import com.movte.slate.domain.user.application.port.FindUserByOauthIdAndOauthProviderPort;
import com.movte.slate.domain.user.application.service.request.LoginServiceRequest;
import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;
import com.movte.slate.domain.user.application.usecase.LoginUseCase;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService implements LoginUseCase {
    private final DecodeIdTokenUseCase decodeIdTokenUseCase;
    private final FindUserByOauthIdAndOauthProviderPort findUserByOauthIdAndOauthProviderPort;
    private final IssueTokenUseCase issueTokenUseCase;

    @Override
    public LoginServiceResponse login(LoginServiceRequest request) {
        String idTokenValue = request.getIdToken();
        String providerValue = request.getProvider();
        OauthProvider provider;
        try {
            provider = OauthProvider.valueOf(providerValue);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_AVAILIABLE_PROVIDER);
        }
        IdToken idToken = decodeIdTokenUseCase.decodeIdToken(idTokenValue);
        Optional<User> userOpt = findUserByOauthIdAndOauthProviderPort.findByOauthIdAndOauthProvider(idToken.getOauthId(), provider);
        if (userOpt.isEmpty()) {
            return new LoginServiceResponse(true, null, null);
        }
        User user = userOpt.get();
        String accessToken = issueTokenUseCase.createAccessToken(user.getId());
        String refreshToken = issueTokenUseCase.createRefreshToken(user.getId());
        return new LoginServiceResponse(true, accessToken, refreshToken);
    }
}
