package com.movte.slate.domain.user.api.request;

import com.movte.slate.domain.user.application.service.request.LoginServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginApiRequest {
    @NotBlank
    private String idToken;
    @NotBlank
    private String provider;

    public LoginServiceRequest toServiceRequest() {
        return new LoginServiceRequest(idToken, provider);
    }
}
