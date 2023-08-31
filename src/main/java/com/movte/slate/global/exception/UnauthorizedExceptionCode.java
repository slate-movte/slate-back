package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UnauthorizedExceptionCode {
    TOKEN_EXPIRED("001", "토큰이 만료되었습니다."),
    NOT_USER("002", "회원이 아닙니다."),
    INVALID_TOKEN("003", "유효한 토큰이 아닙니다."),
    NO_TOKEN("004", "토큰이 존재하지 않습니다."),
    LOGOUT_TOKEN("005", "로그아웃 처리된 토큰입니다."),
    NOT_REFRESH_TOKEN("006", "리프레쉬 토큰이 아닙니다."),
    NOT_ACCESS_TOKEN("007", "액세스 토큰이 아닙니다."),
    NOT_ENOUGH_INFO("008", "회원 추가 정보가 입력되지 않았습니다."),
    ;
    private final String code;
    private final String descriptionMessage;

    public boolean isTokenExpired() {
        return this.equals(TOKEN_EXPIRED);
    }
}
