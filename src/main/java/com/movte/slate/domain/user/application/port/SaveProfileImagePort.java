package com.movte.slate.domain.user.application.port;

import org.springframework.web.multipart.MultipartFile;

public interface SaveProfileImagePort {
    String save(MultipartFile file, long userId);
}
