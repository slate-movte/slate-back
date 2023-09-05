package com.movte.slate.domain.user.application.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    private final boolean isNewUser;
    private final String accessToken;
    private final String refreshToken;
}
