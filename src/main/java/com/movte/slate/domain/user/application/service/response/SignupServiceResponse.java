package com.movte.slate.domain.user.application.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupServiceResponse {
    private final String accessToken;
    private final String refreshToken;
}
