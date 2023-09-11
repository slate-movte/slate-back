package com.movte.slate.kakao.application.service;

import com.movte.slate.kakao.application.usecase.GetDataFromInternetUseCase;
import com.movte.slate.kakao.application.usecase.GetKakaoPublicKeyFromKakaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetKakaoPublicKeyFromKakaoService implements GetKakaoPublicKeyFromKakaoUseCase {
    private final GetDataFromInternetUseCase getDataFromInternetUseCase;
    @Value("${kakao.openKeyKakaoUrl}")
    private String KAKAO_URL;

    @Override
    public String get() {
        return getDataFromInternetUseCase.get(KAKAO_URL);
    }
}
