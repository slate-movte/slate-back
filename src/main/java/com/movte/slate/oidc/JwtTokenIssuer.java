package com.movte.slate.oidc;

import com.movte.slate.domain.user.application.service.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.movte.slate.oidc.JwtToken.ACCESS;
import static com.movte.slate.oidc.JwtToken.REFRESH;

@Component
@RequiredArgsConstructor
public class JwtTokenIssuer {

    private final JwtConfigProperties jwtConfigProperties;

    public String createAccessToken(UserDto userDto) {
        Claims claims = Jwts.claims();
        claims.put("id", userDto.getId());
        String userState = userDto.getUserState().name();
        claims.put("userState", userState);
        return createJwt(claims, ACCESS, jwtConfigProperties.getAccessTokenValidTimeInMillisecondUnit());
    }

    public String createRefreshToken(UserDto userDto) {
        Claims claims = Jwts.claims();
        claims.put("id", userDto.getId());
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
