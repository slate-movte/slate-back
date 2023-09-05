package com.movte.slate.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface SaveProfileImagePort {
    Optional<String> save(MultipartFile file, long userId);
}
