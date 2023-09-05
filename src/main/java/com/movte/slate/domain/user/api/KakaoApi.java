package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.service.PublicKeyGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoApi {
    private final PublicKeyGetter publicKeyGetter;

    /* 카카오로부터 OIDC Public Key 를 가져온다.
    Redis에 캐싱한다.*/
    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCacheManager")
    @GetMapping("/oidc/kakao/openkeys")
    public String getOpenKeys() {
        return publicKeyGetter.retrieveKakaoOpenKeysFromKakao();
    }
}
