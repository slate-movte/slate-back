package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadRequestExceptionCode {
    INVALID_INPUT("001", "잘못된 입력 값입니다."),
    NOT_MATCH_ATTRACTION("002", "잘못된 id 입니다."); // attractionId와 attractionType 이 매치가 안된 경우
    private final String code;
    private final String descriptionMessage;
}
