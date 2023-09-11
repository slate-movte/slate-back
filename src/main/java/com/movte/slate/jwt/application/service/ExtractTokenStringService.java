package com.movte.slate.jwt.application.service;

import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.jwt.application.usecase.ExtractTokenStringUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ExtractTokenStringService implements ExtractTokenStringUseCase {
    @Override
    public String extractTokenString(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NO_TOKEN);
        }
        return authorizationHeader.substring(7);
    }
}
