package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.snapshot.domain.Scene;

import java.util.List;

public interface FindSceneByMoviePort {
    List<Scene> findByMovie(Movie movie);
}
