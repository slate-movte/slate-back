package com.movte.slate.domain.community.application.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AddCommentServiceRequest {
    private final String content;
}
