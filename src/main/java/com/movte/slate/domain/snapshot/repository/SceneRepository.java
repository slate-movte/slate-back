package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.snapshot.domain.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SceneRepository extends JpaRepository<Scene, Long> {

    List<Scene> findByMovie(Movie movie);
}
