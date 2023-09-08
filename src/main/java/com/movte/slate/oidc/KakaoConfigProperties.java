package com.movte.slate.oidc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "kakao")
@ConstructorBinding
@Getter
public class KakaoConfigProperties {
    private final String iss;
    private final String restApiKey;
    private final String redirectUrl;
    private final String tokenUrl;
    private final String openKeyMyselfUrl;
    private final String openKeyKakaoUrl;
}
