package com.movte.slate.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface SaveSnapShotFilePort {
    Optional<String> saveSnapShot(MultipartFile file, long userId);
}
