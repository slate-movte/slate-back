package com.movte.slate.global.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ResponseFormat<T> {
    private final String message;
    private final T data;
    private Long code;
}
