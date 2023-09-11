package com.movte.slate.domain.user.application.usecase;

import com.movte.slate.jwt.domain.JwtToken;

import java.util.Date;

public interface LogoutUseCase {

    void logout(String accessTokenValue, JwtToken accessToken, Date now);
}
