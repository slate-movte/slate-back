package com.movte.slate.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class FailResponseFormat {
    private final String message;
    private final Long code;
}
