package com.movte.slate.oidc;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtConfigProperties {
    private final String secretKey;
    private final Long accessTokenValidTimeInMinuteUnit;
    private final Long refreshTokenValidTimeInDayUnit;

    public byte[] getSecretKey() {
        return secretKey.getBytes();
    }

    public Long getAccessTokenValidTimeInMillisecondUnit() {
        return Duration.ofMinutes(accessTokenValidTimeInMinuteUnit).toMillis();
    }

    public Long getRefreshTokenValidTimeInMillisecondUnit() {
        return Duration.ofDays(refreshTokenValidTimeInDayUnit).toMillis();
    }
}
