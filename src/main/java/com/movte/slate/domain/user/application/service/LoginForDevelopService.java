package com.movte.slate.domain.user.application.service;


import com.movte.slate.domain.user.application.port.FindUserByOauthIdAndOauthProviderPort;
import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;
import com.movte.slate.domain.user.application.usecase.LoginForDevelopUseCase;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.jwt.application.usecase.IssueTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginForDevelopService implements LoginForDevelopUseCase {

    private final FindUserByOauthIdAndOauthProviderPort findUserByOauthIdAndOauthProviderPort;
    private final IssueTokenUseCase issueTokenUseCase;

    @Override
    @Transactional(readOnly = true)
    public LoginServiceResponse login(String oauthId) {
        User user = findUserByOauthIdAndOauthProviderPort.findByOauthIdAndOauthProvider(oauthId, OauthProvider.KAKAO)
                .orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        String accessToken = issueTokenUseCase.createAccessToken(user.getId());
        String refreshToken = issueTokenUseCase.createRefreshToken(user.getId());
        return new LoginServiceResponse(false, accessToken, refreshToken);
    }
}
