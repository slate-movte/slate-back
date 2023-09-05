package com.movte.slate.domain.user.application.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserInfoGetResponse {
    private final Long id;
    private final String nickname;
    private final String profileImageUrl;
}
