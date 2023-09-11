package com.movte.slate.jwt.application.service;

import com.movte.slate.domain.user.application.port.FindBlackListPort;
import com.movte.slate.domain.user.application.service.request.RefreshAccessTokenServiceRequest;
import com.movte.slate.domain.user.application.service.response.RefreshAccessTokenServiceResponse;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.jwt.application.usecase.CreateJwtTokenUseCase;
import com.movte.slate.jwt.application.usecase.IssueTokenUseCase;
import com.movte.slate.jwt.application.usecase.ReissueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ReissueService implements ReissueUseCase {
    private final CreateJwtTokenUseCase createJwtTokenUseCase;
    private final IssueTokenUseCase issueTokenUseCase;
    private final FindBlackListPort findBlackListPort;

    @Override
    public RefreshAccessTokenServiceResponse reissue(RefreshAccessTokenServiceRequest request) {
        String refreshTokenValue = request.getRefreshTokenValue();
        if (findBlackListPort.find(refreshTokenValue))
            throw new UnauthorizedException(UnauthorizedExceptionCode.LOGOUT_TOKEN);
        Long userId = createJwtTokenUseCase.create(refreshTokenValue).getUserId();
        return new RefreshAccessTokenServiceResponse(issueTokenUseCase.createAccessToken(userId));
    }
}
