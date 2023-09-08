package com.movte.slate.domain.movie.application.service.dto;

import com.movte.slate.domain.snapshot.application.service.dto.SnapShotResponseDto;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class MovieResponseDto {
    private Long id;
    private String title;
    private List<SnapShotResponseDto> scenes;
}
