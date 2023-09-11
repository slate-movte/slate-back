package com.movte.slate.kakao.application.usecase;

import com.movte.slate.oidc.application.response.GetOidcPublicKeysResponse;

public interface GetKakaoPublicKeyFromMyselfUseCase {
    GetOidcPublicKeysResponse get();
}
