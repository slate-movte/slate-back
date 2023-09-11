package com.movte.slate.domain.snapshot.application.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SearchBunchOfSnapshotOfOwnerServiceResponse {
    List<SnapshotResponseDto> scenes;

}
