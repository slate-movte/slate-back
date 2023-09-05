package com.movte.slate.jwt.domain;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RequestUri {
    private final String uri;

    public boolean canAccessWithoutAccessToken() {
        List<String> permittedList = List.of("/oidc/kakao",
                "/user/reissue",
                "/user/tokens",
                "/user/login",
                "/user/signup",
                "/user/nickname/duplicate");
        if (uri == null) {
            throw new ServerErrorException(ServerErrorExceptionCode.INVALID_URI);
        }
        return permittedList.stream().anyMatch(uri::startsWith);
    }
}
