package com.movte.slate.kakao.api;

import com.movte.slate.kakao.application.usecase.GetKakaoPublicKeyFromKakaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetKakaoPublicKeyApi {
    private final GetKakaoPublicKeyFromKakaoUseCase getKakaoPublicKeyFromKakaoUseCase;

    /* 카카오로부터 OIDC Public Key 를 가져온다.
    Redis에 캐싱한다.*/
    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCacheManager")
    @GetMapping("/oidc/kakao/openkeys")
    public String getOpenKeys() {
        return getKakaoPublicKeyFromKakaoUseCase.get();
    }
}
