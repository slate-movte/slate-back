package com.movte.slate.domain.user.api.dto.request;

import com.movte.slate.domain.user.application.service.dto.request.LoginServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {
    @NotBlank
    private String idToken;
    @NotBlank
    private String provider;

    public LoginServiceRequest toServiceRequest() {
        return new LoginServiceRequest(idToken, provider);
    }
}
