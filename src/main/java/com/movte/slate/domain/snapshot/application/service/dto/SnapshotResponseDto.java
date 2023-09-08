package com.movte.slate.domain.snapshot.application.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SnapshotResponseDto {
    private final long id;
    private final String imageUrl;
}
