package com.movte.slate.domain.user.application.service;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.oidc.KakaoConfigProperties;
import com.movte.slate.oidc.dto.OidcPublicKeyDto;
import com.movte.slate.oidc.dto.OidcPublicKeysDto;
import com.movte.slate.util.InternetDataLoader;
import com.movte.slate.util.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetKakaoPublicKeyAdapter {

    private final String OPENKEY_MYSELF_URL;
    private final String OPENKEY_KAKAO_URL;
    private final InternetDataLoader internetDataLoader;

    public GetKakaoPublicKeyAdapter(KakaoConfigProperties kakaoConfigProperties, InternetDataLoader internetDataLoader) {
        this.OPENKEY_MYSELF_URL = kakaoConfigProperties.getOpenKeyMyselfUrl();
        this.OPENKEY_KAKAO_URL = kakaoConfigProperties.getOpenKeyKakaoUrl();
        this.internetDataLoader = internetDataLoader;
    }

    public OidcPublicKeysDto getPublicKeys() {
        return retrieveKakaoOpenKeysFromMyself();
    }

    public String retrieveKakaoOpenKeysFromKakao() {
        try {
            return internetDataLoader.getResponseUsingGetHttpMethod(OPENKEY_KAKAO_URL);
        } catch (IOException e) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
    }

    private OidcPublicKeysDto retrieveKakaoOpenKeysFromMyself() {
        String openKey = null;
        try {
            openKey = internetDataLoader.getResponseUsingGetHttpMethod(OPENKEY_MYSELF_URL);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
        String array = JsonParser.getObject(openKey, "keys");
        return new OidcPublicKeysDto(JsonParser.parse(array, OidcPublicKeyDto.class));
    }
}
