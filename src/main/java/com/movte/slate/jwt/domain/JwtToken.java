package com.movte.slate.jwt.domain;

import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.domain.user.domain.UserState;
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
        return body.getExpiration().before(now);
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
        return Long.parseLong(getValue("id").orElseThrow(() -> new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN)));
    }

    public UserState getUserState() {
        return UserState.valueOf(getValue("userState").orElseThrow(() -> new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN)));
    }
}