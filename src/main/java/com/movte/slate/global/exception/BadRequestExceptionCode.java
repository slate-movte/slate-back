package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadRequestExceptionCode {
    INVALID_INPUT("001", "잘못된 입력 값입니다.");
    private final String code;
    private final String descriptionMessage;
}
