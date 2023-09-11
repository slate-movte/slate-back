package com.movte.slate.kakao.application.service;

import com.movte.slate.kakao.application.usecase.GetDataFromInternetUseCase;
import com.movte.slate.kakao.application.usecase.GetKakaoPublicKeyFromMyselfUseCase;
import com.movte.slate.kakao.util.JsonParser;
import com.movte.slate.oidc.application.response.GetOidcPublicKeysResponse;
import com.movte.slate.oidc.application.response.OidcPublicKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetKakaoPublicKeyFromMyselfService implements GetKakaoPublicKeyFromMyselfUseCase {
    private final GetDataFromInternetUseCase getDataFromInternetUseCase;
    @Value("${kakao.openKeyMyselfUrl}")
    private String MYSELF_URL;

    @Override
    public GetOidcPublicKeysResponse get() {
        String openKey = getDataFromInternetUseCase.get(MYSELF_URL);
        String array = JsonParser.getObject(openKey, "keys");
        return new GetOidcPublicKeysResponse(JsonParser.parse(array, OidcPublicKeyDto.class));
    }
}
