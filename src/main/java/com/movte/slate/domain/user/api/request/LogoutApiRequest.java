package com.movte.slate.domain.user.api.request;

import lombok.Getter;

@Getter
public class LogoutApiRequest {
    private String refreshToken;
}
