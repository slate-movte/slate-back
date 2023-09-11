package com.movte.slate.domain.community.application.port;

import org.springframework.web.multipart.MultipartFile;

public interface SaveSnapShotFilePort {
    String saveSnapShot(MultipartFile file, long userId);
}
