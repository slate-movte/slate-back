package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.port.SaveSnapShotFilePort;
import com.movte.slate.global.s3.S3FileSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Service
public class SaveSnapshotFileService implements SaveSnapShotFilePort {
    private final S3FileSaver s3FileSaver;

    @Override
    public String saveSnapShot(MultipartFile file, long userId) {
        String fileName = String.valueOf(userId);
        String[] strings = requireNonNull(file.getOriginalFilename()).split("\\.");
        String fileType = strings[strings.length - 1];
        return s3FileSaver.save(file, "images/snapshot/", fileName, fileType);
    }
}
