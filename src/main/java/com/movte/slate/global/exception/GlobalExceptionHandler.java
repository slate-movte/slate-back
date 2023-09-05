package com.movte.slate.global.exception;

import com.movte.slate.global.response.FailResponse;
import com.movte.slate.global.response.FieldError;
import com.movte.slate.global.response.ResponseFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpBusinessException.class)
    public ResponseEntity<FailResponse> httpBusinessException(HttpBusinessException e) {
        return ResponseFactory.fail(e);
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
