package com.movte.slate.domain.user.application.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetUserInfoResponse {
    private final Long id;
    private final String nickname;
    private final String profileImageUrl;
}
