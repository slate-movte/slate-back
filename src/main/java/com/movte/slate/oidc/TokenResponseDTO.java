package com.movte.slate.oidc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TokenResponseDTO {
    private String access_token;
    private String refresh_token;
}
