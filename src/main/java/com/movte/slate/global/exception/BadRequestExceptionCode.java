package com.movte.slate.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadRequestExceptionCode {
    INVALID_INPUT("001", "잘못된 입력 값입니다."),
    // attractionId와 attractionType 이 매치가 안된 경우
    NOT_MATCH_ATTRACTION("002", "잘못된 id 입니다."),
    NOT_USER("003", "회원이 아닙니다."),
    NO_REFRESH_TOKEN("004", "리프레쉬 토큰이 없습니다."),
    REFRESH_TOKEN_NOT_EQUAL("005", "DB에 있는 리프레쉬 토큰과 다릅니다."),
    NOT_AVAILIABLE_PROVIDER("006", "유효하지 않은 Oauth Provider입니다."),
    ALREADY_USER("007", "이미 회원 가입된 유저입니다."),
    DUPLICATE_NICKNAME("008", "닉네임이 중복됩니다."),
    NO_ONE_PICTURE("009", "클라이언트로부터 입력 받은 사진이 한 장이 아닙니다."),
    NOT_IMAGE_FORMAT("010", "이미지 파일 포맷이 아닙니다."),
    NO_RESOURCE("011","자원이 존재하지 않습니다");
    private final String code;
    private final String descriptionMessage;
}
