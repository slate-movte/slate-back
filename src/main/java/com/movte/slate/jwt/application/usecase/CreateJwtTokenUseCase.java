package com.movte.slate.jwt.application.usecase;

import com.movte.slate.jwt.domain.JwtToken;

public interface CreateJwtTokenUseCase {
    JwtToken create(String tokenString);
}