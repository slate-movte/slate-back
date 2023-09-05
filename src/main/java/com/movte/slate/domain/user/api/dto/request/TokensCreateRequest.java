package com.movte.slate.domain.user.api.dto.request;

import com.movte.slate.domain.user.application.service.dto.request.TokensCreateServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TokensCreateRequest {
    @NotBlank
    private String idToken;

    public TokensCreateServiceRequest toServiceRequest() {
        return new TokensCreateServiceRequest(idToken);
    }
}
