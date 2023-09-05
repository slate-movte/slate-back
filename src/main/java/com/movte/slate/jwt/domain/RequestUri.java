package com.movte.slate.jwt.domain;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestUri {
    private final String uri;

    public boolean canAccessWithoutAccessToken() {
        if (uri == null) {
            throw new ServerErrorException(ServerErrorExceptionCode.INVALID_URI);
        }
        return uri.startsWith("/oidc/kakao") ||
                uri.startsWith("/user/reissue") ||
                uri.startsWith("/user/tokens") ||
                uri.startsWith("/user/login") ||
                uri.startsWith("/user/signup");
    }
}
