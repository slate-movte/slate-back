package com.movte.slate.oidc.application.usecase;

import com.movte.slate.oidc.domain.IdToken;

public interface DecodeIdTokenUseCase {
    IdToken decodeIdToken(String idToken);
}
