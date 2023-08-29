package com.movte.slate.global.exception;

import com.movte.slate.global.response.FailResponse;
import com.movte.slate.global.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpBusinessException.class)
    public ResponseEntity<FailResponse> httpBusinessException(HttpBusinessException e) {
        return ResponseFactory.fail(e);
    }
}
