package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadRequestExceptionCode {
    ;
    private final Long code;
    private final String descriptionMessage;
}
