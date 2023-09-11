package com.movte.slate.jwt.application.service;

import com.movte.slate.jwt.application.usecase.IssueTokenUseCase;
import com.movte.slate.jwt.config.JwtConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.movte.slate.jwt.domain.JwtToken.ACCESS;
import static com.movte.slate.jwt.domain.JwtToken.REFRESH;

@RequiredArgsConstructor
@Service
class IssueTokenService implements IssueTokenUseCase {
    private final JwtConfigProperties jwtConfigProperties;

    @Override
    public String createAccessToken(Long id) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        return createJwt(claims, ACCESS, jwtConfigProperties.getAccessTokenValidTimeInMillisecondUnit());
    }

    @Override
    public String createRefreshToken(Long id) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        return createJwt(claims, REFRESH, jwtConfigProperties.getRefreshTokenValidTimeInMillisecondUnit());
    }

    public String createJwt(Claims claims, String tokenType, Long tokenValidTime) {
        return Jwts.builder()
                .setHeaderParam("type", tokenType)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, jwtConfigProperties.getSecretKey())
                .compact();
    }
}
