package com.movte.slate.global.exception;

import com.movte.slate.global.response.FailResponse;
import com.movte.slate.global.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpBusinessException.class)
    public ResponseEntity<FailResponse> httpBusinessException(HttpBusinessException e) {
        return ResponseFactory.fail(e);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<FailResponse> serverErrorException(ServerErrorException e) {
        log.info("서버 에러 예외 발생! << code: {}", e.getCode());
        return ResponseFactory.failWithServerError();
    }
}
