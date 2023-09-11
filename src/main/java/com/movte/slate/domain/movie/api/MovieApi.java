package com.movte.slate.domain.movie.api;

import com.movte.slate.domain.movie.application.usecase.MovieSearchUseCase;
import com.movte.slate.global.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieApi {

    private final MovieSearchUseCase movieSearchUseCase;

    @GetMapping("/search/movie/{movieId}")
    public ResponseEntity<?> searchMovie(@PathVariable("movieId") Long movieId) {
        return ResponseFactory.success(movieSearchUseCase.searchDetail(movieId));
    }
}
