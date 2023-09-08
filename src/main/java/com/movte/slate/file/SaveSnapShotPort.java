package com.movte.slate.file;

import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface SaveSnapShotPort {
    Optional<String> saveSnapShot(MultipartFile file, long userId);
}
