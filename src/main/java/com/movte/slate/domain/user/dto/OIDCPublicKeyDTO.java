package com.movte.slate.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OIDCPublicKeyDTO {
    private String kid;

    private String kty;

    private String alg;

    private String use;

    private String n;

    private String e;
}