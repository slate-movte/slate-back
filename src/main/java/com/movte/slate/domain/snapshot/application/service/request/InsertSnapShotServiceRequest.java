package com.movte.slate.domain.snapshot.application.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Getter
public class InsertSnapShotServiceRequest {
    private final long sceneId;
    private final MultipartFile file;
}
