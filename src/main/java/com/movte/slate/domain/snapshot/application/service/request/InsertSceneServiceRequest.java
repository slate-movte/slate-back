package com.movte.slate.domain.snapshot.application.service.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class InsertSceneServiceRequest {
    private final String address;
    private final String sidoCode;
    private final String gugunCode;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String movieTitle;
    private final MultipartFile image;
    private final String sceneLocation;
}
