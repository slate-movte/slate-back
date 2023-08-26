package com.movte.slate.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class NotFoundException extends HttpBusinessException {
    private final NotFoundExceptionCode code;

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
        return HttpStatus.NOT_FOUND.value();
    }

}
