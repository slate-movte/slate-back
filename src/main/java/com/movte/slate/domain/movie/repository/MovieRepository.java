package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.domain.MovieBefore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

}
