package com.movte.slate.domain.user.api.dto.request;

import com.movte.slate.domain.user.application.service.dto.request.PendingUserInfoSetServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PendingUserInfoSetRequest {
    @NotBlank
    private String nickname;
    @NotBlank
    private String profile_image_url;

    public PendingUserInfoSetServiceRequest toServiceRequest() {
        return new PendingUserInfoSetServiceRequest(nickname, profile_image_url);
    }
}
