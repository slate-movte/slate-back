package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ServerErrorException extends HttpBusinessException {

    private final ServerErrorExceptionCode serverErrorExceptionCode;

    @Override
    public String getCode() {
        return serverErrorExceptionCode.getCode(); // 서버 내부 오류의 에러 코드는 공개하지 않는다.
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
