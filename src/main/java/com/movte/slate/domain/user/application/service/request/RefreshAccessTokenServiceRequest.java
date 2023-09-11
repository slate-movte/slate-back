package com.movte.slate.domain.user.application.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RefreshAccessTokenServiceRequest {
    private final String refreshTokenValue;

}
