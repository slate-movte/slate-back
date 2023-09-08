package com.movte.slate.domain.snapshot.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapShotResponseDto {

    private long scenId;
    private String imageUrl;
}
