package com.movte.slate.jwt.application.service;

import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.jwt.application.usecase.CreateJwtTokenUseCase;
import com.movte.slate.jwt.config.JwtConfigProperties;
import com.movte.slate.jwt.domain.JwtToken;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateJwtTokenService implements CreateJwtTokenUseCase {
    private final JwtConfigProperties jwtConfigProperties;

    @Override
    public JwtToken create(String tokenString) {
        byte[] secretKey = jwtConfigProperties.getSecretKey();
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenString);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.TOKEN_EXPIRED);
        }
        return new JwtToken(claimsJws);
    }
}
