package com.movte.slate.domain.stillcut.application.service.response;

import com.movte.slate.domain.movie.application.service.dto.MovieResponseDto;
import java.util.List;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SearchMovieTitleServiceResponse {
    private List<MovieResponseDto> movies;
}
