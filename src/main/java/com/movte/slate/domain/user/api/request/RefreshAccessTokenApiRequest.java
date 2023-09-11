package com.movte.slate.domain.user.api.request;

import com.movte.slate.domain.user.application.service.request.RefreshAccessTokenServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RefreshAccessTokenApiRequest {
    @NotBlank
    private String refreshToken;

    public RefreshAccessTokenServiceRequest toServiceRequest() {
        return new RefreshAccessTokenServiceRequest(refreshToken);
    }
}
