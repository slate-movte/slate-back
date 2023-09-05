package com.movte.slate.domain.user.api.dto.request;

import com.movte.slate.domain.user.application.service.dto.request.EditUserInfoServiceRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class EditUserInfoRequest {
    private String nickname;
    private MultipartFile file;

    public EditUserInfoServiceRequest toServiceRequest() {
        return new EditUserInfoServiceRequest(nickname, file);
    }
}
