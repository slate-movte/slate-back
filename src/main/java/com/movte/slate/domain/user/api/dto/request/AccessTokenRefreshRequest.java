package com.movte.slate.domain.user.api.dto.request;

import com.movte.slate.domain.user.application.service.dto.request.AccessTokenRefreshServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AccessTokenRefreshRequest {
    @NotBlank
    private String refreshToken;

    public AccessTokenRefreshServiceRequest toServiceRequest() {
        return new AccessTokenRefreshServiceRequest(refreshToken);
    }
}
