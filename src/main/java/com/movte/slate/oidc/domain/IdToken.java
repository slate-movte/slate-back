package com.movte.slate.oidc.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class IdToken {
    // issuer
    private final String iss;
    // client id
    private final String aud;
    // oauth id
    private final String sub;

    private final String email;

    private final String nickname;

    private final String profile_img_url;

    public String getOauthId() {
        return sub;
    }
}