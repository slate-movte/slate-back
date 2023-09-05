package com.movte.slate.domain.user.api.dto.request;

import com.movte.slate.domain.user.application.service.dto.request.SignupServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignupRequest {
    @NotBlank
    String idToken;
    @NotBlank
    String nickname;
    @NotBlank
    String profileImageUrl;
    @NotBlank
    String provider;

    public SignupServiceRequest toServiceRequest() {
        return new SignupServiceRequest(idToken, nickname, profileImageUrl, provider);
    }
}
