package com.movte.slate.global.exception;

import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.ResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpBusinessException.class)
    public ResponseEntity<ResponseFormat<String>> httpBusinessException(HttpBusinessException e) {
        return ResponseFactory.fail(e);
    }
}
