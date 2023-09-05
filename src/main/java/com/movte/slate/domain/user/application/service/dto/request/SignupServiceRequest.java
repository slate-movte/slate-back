package com.movte.slate.domain.user.application.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignupServiceRequest {
    private final String idToken;
    private final String nickname;
    private final String profileImageUrl;
    private final String provider;
}
