package com.movte.slate.domain.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserState {
    PENDING("회원 가입은 되었지만, 아직 추가 정보를 입력 받지 못한 회원 상태"),
    APPROVED("회원 가입 & 추가 정보 입력 받은 상태"),
    ;
    private final String text;
}
