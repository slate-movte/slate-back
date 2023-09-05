package com.movte.slate.domain.user.application.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenRefreshServiceRequest {
    private final String refreshTokenValue;

}
