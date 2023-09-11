package com.movte.slate.domain.attraction.application.usecase;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.dto.request.IntegrationSearchRequestDto;
import com.movte.slate.domain.attraction.dto.response.AttractionListResponseDto;
import com.movte.slate.domain.attraction.dto.response.IntegrationSearchResponseDto;
import com.movte.slate.domain.attraction.dto.response.MovieListResponseDto;
import com.movte.slate.domain.attraction.repository.AttractionRepository;
import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 *  영화 / 관광지 검색 처리.
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IntegrationSearchUseCase {

    private final AttractionRepository attractionRepository;
    private final MovieRepository movieRepository;
    private final Long LAST_ID = -1L;

    public IntegrationSearchResponseDto search(IntegrationSearchRequestDto requestDto) {
        List<Attraction> attractionList = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>();
        String keyword = requestDto.getKeyword();
        Long lastAttractionId = requestDto.getAttractionLastId();
        Long lastMovieId = requestDto.getMovieLastId();

        if (isContinuableSearchAttraction(lastAttractionId)) {
            List<Attraction> queryResult =
                attractionRepository.findTop10ByTitleContainsAndIdAfterOrderByIdAsc(keyword,
                    lastAttractionId);
            attractionList.addAll(queryResult);
        }

        if (isContinuableSearchMovie(lastMovieId)) {
            List<Movie> queryResult = movieRepository.selectListMovieWithActorByKeywordAndLastId(
                keyword, lastMovieId);
            movieList.addAll(queryResult);
        }
        return IntegrationSearchResponseDto.builder()
            .attractionList(attractionList.stream().map(AttractionListResponseDto::from).toList())
            .movieList(movieList.stream().map(MovieListResponseDto::from).toList())
            .build();
    }

    private boolean isContinuableSearchAttraction(Long lastAttractionId) {
        return !lastAttractionId.equals(LAST_ID);
    }

    private boolean isContinuableSearchMovie(Long lastMovieId) {
        return !lastMovieId.equals(LAST_ID);
    }
}
