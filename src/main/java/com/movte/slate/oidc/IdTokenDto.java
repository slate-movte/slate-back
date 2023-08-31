package com.movte.slate.oidc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class IdTokenDto {
    /**
     * issuer ex https://kauth.kakao.com
     */
    private final String iss;
    /**
     * client id
     */
    private final String aud;
    /**
     * oauth provider account unique id
     */
    private final String sub;

    private final String email;

    private final String nickname;

    private final String profile_img_url;

    public String getOauthId() {
        return sub;
    }
}