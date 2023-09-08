package com.movte.slate.util;

import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;

public class TokenStringExtractor {
    public static String extractTokenString(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NO_TOKEN);
        }
        return authorizationHeader.substring(7);
    }
}
