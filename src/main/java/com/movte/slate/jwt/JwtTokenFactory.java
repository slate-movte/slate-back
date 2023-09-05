package com.movte.slate.jwt;

import com.movte.slate.jwt.domain.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {
    private final JwtConfigProperties jwtConfigProperties;

    public JwtToken create(String tokenString) {
        byte[] secretKey = jwtConfigProperties.getSecretKey();
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenString);
        return new JwtToken(claimsJws);
    }
}
