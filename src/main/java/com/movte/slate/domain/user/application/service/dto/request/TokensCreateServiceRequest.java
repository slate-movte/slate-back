package com.movte.slate.domain.user.application.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokensCreateServiceRequest {
    private final String idToken;
}
