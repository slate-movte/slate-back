package com.movte.slate.oidc;

import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import org.springframework.stereotype.Component;

@Component
public class TokenStringExtractor {
    public String extractTokenString(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NO_TOKEN);
        }
        return authorizationHeader.substring(7);
    }
}
