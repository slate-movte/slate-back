package com.movte.slate.domain.snapshot.application.service.response;

import com.movte.slate.domain.movie.dto.MovieResponseDto;
import java.util.List;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SearchBunchOfSceneWithMovieTitleServiceResponse {
    private List<MovieResponseDto> movies;
}
