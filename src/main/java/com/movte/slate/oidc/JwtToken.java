package com.movte.slate.oidc;

import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import lombok.Getter;

import java.util.Date;
import java.util.Optional;

@Getter
public class JwtToken {
    public static final String ACCESS = "access";
    public static final String REFRESH = "refresh";
    private final Jws<Claims> claimsJws;

    public JwtToken(Jws<Claims> claimsJws) {
        this.claimsJws = claimsJws;
    }

    public Optional<String> getValue(String key) {
        Claims body = claimsJws.getBody();
        return Optional.ofNullable(body.get(key).toString());
    }

    public boolean isExpired(Date now) {
        Claims body = claimsJws.getBody();
        return body.getExpiration().after(now);
    }

    public boolean isAccessToken() {
        String type = claimsJws.getHeader().get("type").toString();
        return ACCESS.equals(type);
    }

    public boolean isRefreshToken() {
        String type = claimsJws.getHeader().get("type").toString();
        return REFRESH.equals(type);
    }

    public Long getUserId() throws ExpiredJwtException {
        return Long.parseLong(getValue("userId").orElseThrow(() -> new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN)));
    }
}