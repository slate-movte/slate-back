package com.movte.slate.oidc;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OidcPublicKeys {
    private final List<OidcPublicKeyDTO> oidcPublicKeys;

    public OidcPublicKeyDTO getTargetOidcPublicKey(String kid) {
        return oidcPublicKeys.stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.NO_OIDC_PUBLIC_KEY));
    }
}
