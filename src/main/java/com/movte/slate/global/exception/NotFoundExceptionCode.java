package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotFoundExceptionCode {
    NOT_FOUND_ATTRACTION("001", "해당 관광지를 찾을 수 없습니다.");

    private final String code;
    private final String descriptionMessage;
}