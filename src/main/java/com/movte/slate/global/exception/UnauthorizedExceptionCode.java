package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UnauthorizedExceptionCode {
    TOKEN_EXPIRED(1L, "토큰이 만료되었습니다."),
    NOT_USER(2L, "회원이 아닙니다."),
    INVALID_TOKEN(3L, "유효한 토큰이 아닙니다."),
    NO_TOKEN(4L, "토큰이 존재하지 않습니다."),
    LOGOUT_TOKEN(5L, "로그아웃 처리된 토큰입니다."),
    NOT_REFRESH_TOKEN(6L, "리프레쉬 토큰이 아닙니다."),
    NOT_ACCESS_TOKEN(7L, "액세스 토큰이 아닙니다."),
    ;
    private final Long code;
    private final String descriptionMessage;

    public boolean isTokenExpired() {
        return this.equals(TOKEN_EXPIRED);
    }
}
