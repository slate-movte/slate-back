package com.movte.slate.domain.stillcut.application.service.response;

import com.movte.slate.domain.movie.application.service.dto.MovieResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchMovieTitleServiceResponse {
    private List<MovieResponseDto> movies;
}
