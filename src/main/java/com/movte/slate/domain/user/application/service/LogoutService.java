package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.port.PutAccessTokenToBlackListTokenPort;
import com.movte.slate.domain.user.application.usecase.LogoutUseCase;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {
    private final PutAccessTokenToBlackListTokenPort putAccessTokenToBlackListTokenPort;

    @Override
    public void logout(String accessTokenValue, JwtToken accessToken, Date now) {
        Date expiredAt = accessToken.getExpiredAt();
        putAccessTokenToBlackListTokenPort.blackList(accessTokenValue, expiredAt, now);
    }
}
