package com.movte.slate.oidc.application.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OidcPublicKeyDto {
    private String kid;

    private String kty;

    private String alg;

    private String use;

    private String n;

    private String e;
}