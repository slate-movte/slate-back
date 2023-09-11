package com.movte.slate.domain.movie.dto;

import com.movte.slate.domain.snapshot.application.service.dto.SceneResponseDto;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class MovieResponseDto {
    private Long id;
    private String title;
    private List<SceneResponseDto> scenes;
}
