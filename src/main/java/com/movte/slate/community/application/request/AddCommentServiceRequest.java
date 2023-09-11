package com.movte.slate.community.application.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AddCommentServiceRequest {
    private final String content;
}
