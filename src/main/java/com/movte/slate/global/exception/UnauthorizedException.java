package com.movte.slate.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class UnauthorizedException extends HttpBusinessException {
    private final UnauthorizedExceptionCode code;

    @Override
    public Long getCode() {
        return code.getCode();
    }

    @Override
    public String getMessage() {
        return code.getDescriptionMessage();
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    public boolean isTokenExpired() {
        return code.isTokenExpired();
    }
}
