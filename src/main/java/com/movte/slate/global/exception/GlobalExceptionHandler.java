package com.movte.slate.global.exception;

import com.movte.slate.global.response.FailResponse;
import com.movte.slate.global.response.ResponseFactory;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpBusinessException.class)
    public ResponseEntity<FailResponse> httpBusinessException(HttpBusinessException e) {
        e.printStackTrace();
        return ResponseFactory.fail(e);
    }


    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<FailResponse> serverErrorException(ServerErrorException e) {
        log.info("서버 에러 예외 발생! << code: {}", e.getCode());
        e.printStackTrace();
        return ResponseFactory.failWithServerError();
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<FailResponse> signatureException(SignatureException e) {
        log.info("유효하지 않은 토큰 입력!");
        return ResponseFactory.fail(new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN));
    }

    // query string validation 검사 후 실패 하면 호출된다.
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<FailResponse> bindException(BindException e) {
        BadRequestExceptionCode invalidInput = BadRequestExceptionCode.INVALID_INPUT;
        String message = getBindingResultMessage(e.getBindingResult(),
                invalidInput.getDescriptionMessage());

        FailResponse failResponse = new FailResponse(message, invalidInput.getCode());
        return ResponseFactory.fail(failResponse, HttpStatus.BAD_REQUEST);
    }


    private String getBindingResultMessage(BindingResult bindingResult, String defaultMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(defaultMessage).append("] ")
                .append(this.getBindingResultErrorMessage(bindingResult));
        return sb.toString();
    }

    private String getBindingResultErrorMessage(BindingResult bindingResult) {
        ObjectError objectError = bindingResult.getAllErrors()
                .stream()
                .findFirst()
                .get();
        return objectError.getDefaultMessage();
    }


}
