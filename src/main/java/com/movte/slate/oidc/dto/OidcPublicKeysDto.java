package com.movte.slate.oidc.dto;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OidcPublicKeysDto {
    private final List<OidcPublicKeyDto> oidcPublicKeys;

    public OidcPublicKeyDto getTargetOidcPublicKey(String kid) {
        return oidcPublicKeys.stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.NO_OIDC_PUBLIC_KEY));
    }
}
