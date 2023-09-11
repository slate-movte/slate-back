package com.movte.slate.domain.attraction.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IntegrationSearchResponseDto {

    private List<MovieListResponseDto> movieList;
    private List<AttractionListResponseDto> attractionList;

    @Builder
    public IntegrationSearchResponseDto(List<MovieListResponseDto> movieList,
        List<AttractionListResponseDto> attractionList) {
        this.movieList = movieList;
        this.attractionList = attractionList;
    }
}
