package com.movte.slate.domain.snapshot.application.service.response;

import com.movte.slate.domain.snapshot.application.service.dto.SnapshotResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SearchBunchOfSnapshotOfOwnerServiceResponse {
    List<SnapshotResponseDto> scenes;

}
