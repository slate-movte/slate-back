package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleIsContaining(String title);
}
