package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.snapshot.domain.Scene;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface SaveImagePort {
    Optional<String> saveScene(MultipartFile file, long movieId);
}
