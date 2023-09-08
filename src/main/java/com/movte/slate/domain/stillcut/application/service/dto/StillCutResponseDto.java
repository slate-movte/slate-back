package com.movte.slate.domain.stillcut.application.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StillCutResponseDto {
    private final long id;
    private final String imageUrl;
}