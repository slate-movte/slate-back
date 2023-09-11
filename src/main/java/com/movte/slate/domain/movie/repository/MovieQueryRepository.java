package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Movie;
import java.util.List;

public interface MovieQueryRepository {

    List<Movie> selectListMovieWithActorByKeywordAndLastId(String keyword, Long lastId);

    Movie selectMovieWithActorAndScene(Long movieId);
}
