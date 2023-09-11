package com.movte.slate.jwt.application.service;

import com.movte.slate.jwt.application.usecase.InvalidateUseCase;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class InvalidateService implements InvalidateUseCase {
    @Override
    public void invalidate(JwtToken accessToken) {

    }
}
