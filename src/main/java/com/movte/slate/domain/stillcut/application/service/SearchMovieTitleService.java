package com.movte.slate.domain.stillcut.application.service;

import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.stillcut.application.service.response.SearchMovieTitleServiceResponse;
import com.movte.slate.domain.movie.repository.FindMovieByTitlePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchMovieTitleService {
    private final FindMovieByTitlePort findMovieByTitlePort;

    public SearchMovieTitleServiceResponse searchMovieTitle(String title) {
        List<Movie> movies = findMovieByTitlePort.findByTitle(title);
        // 영화 리스트 받아두기 끝 여이서부터 다시 작업
        return null;
    }

}
