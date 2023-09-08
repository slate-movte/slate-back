package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Movie;

import java.util.List;

public interface FindMovieByTitlePort {

    List<Movie> findByTitle(String title);
}
