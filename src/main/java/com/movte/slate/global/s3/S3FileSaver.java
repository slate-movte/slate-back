package com.movte.slate.global.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3FileSaver {
    private final AmazonS3Client amazonS3Client;
    @Value("${s3.bucket}")
    private String bucket;
    @Value("${s3.region}")
    private String region;

    public String save(MultipartFile file, String path, String fileName, String fileType) {
        try {
            fileName = path + fileName + "." + fileType;
            String fileUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), objectMetadata);
            return fileUrl;
        } catch (IOException e) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
    }
}
