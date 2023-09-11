package com.movte.slate.domain.movie.application.service.dto;

import com.movte.slate.domain.snapshot.application.service.response.SceneResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
