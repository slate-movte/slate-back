package com.movte.slate.domain.user.application.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Getter
public class EditUserInfoServiceRequest {
    private final String nickname;
    private final MultipartFile file;
}
