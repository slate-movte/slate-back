package com.movte.slate.oidc;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OidcPublicKeyDTO {
    private String kid;

    private String kty;

    private String alg;

    private String use;

    private String n;

    private String e;
}