package com.movte.slate.domain.snapshot.application.service.response;


import com.movte.slate.domain.movie.domain.Movie;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SceneDetailMovieTitleResponse {

    private Long movieId;
    private String title;

    @Builder
    public SceneDetailMovieTitleResponse(Long movieId, String title) {
        this.movieId = movieId;
        this.title = title;
    }

    public static SceneDetailMovieTitleResponse from(Movie movie) {
        return SceneDetailMovieTitleResponse.builder()
            .movieId(movie.getMovieId())
            .title(movie.getTitle())
            .build();
    }
}
