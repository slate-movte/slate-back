package com.movte.slate.domain.user.application.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenRefreshResponse {
    private final String accessToken;
}
