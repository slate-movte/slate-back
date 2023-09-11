package com.movte.slate.domain.user.api.request;

import com.movte.slate.domain.user.application.service.request.EditUserInfoServiceRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class EditUserInfoApiRequest {
    private String nickname;
    private MultipartFile file;

    public EditUserInfoServiceRequest toServiceRequest() {
        return new EditUserInfoServiceRequest(nickname, file);
    }
}
