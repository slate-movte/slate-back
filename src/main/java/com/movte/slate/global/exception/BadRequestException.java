package com.movte.slate.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class BadRequestException extends HttpBusinessException {
    private final BadRequestExceptionCode code;

    @Override
    public String getCode() {
        return code.getCode();
    }

    @Override
    public String getMessage() {
        return code.getDescriptionMessage();
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
