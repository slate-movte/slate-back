package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotFoundExceptionCode {
    NOT_FOUND_ATTRACTION("001", "해당 관광지를 찾을 수 없습니다."),
    NOT_FOUND_MOVIE("002","해당 영화를 찾을 수 없습니다."),
    NOT_FOUND_SCENE("003","해당 영화 씬을 찾을 수 없습니다"),
    NOT_FOUND_COURSE("004","해당 코스를 찾을 수 없습니다.");
    private final String code;
    private final String descriptionMessage;
}