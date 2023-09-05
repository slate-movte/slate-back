package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadRequestExceptionCode {
    NOT_USER("001", "회원이 아닙니다."),
    NO_REFRESH_TOKEN("002", "리프레쉬 토큰이 없습니다."),
    REFRESH_TOKEN_NOT_EQUAL("003", "DB에 있는 리프레쉬 토큰과 다릅니다."),
    NOT_AVAILIABLE_PROVIDER("004", "유효하지 않은 Oauth Provider입니다."),
    ALREADY_USER("005", "이미 회원 가입된 유저입니다."),
    ;
    private final String code;
    private final String descriptionMessage;
}
