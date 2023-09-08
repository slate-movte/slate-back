package com.movte.slate.domain.user.application.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginServiceRequest {
    private final String idToken;
    private final String provider;
}
