package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Director;
import com.movte.slate.domain.movie.domain.DirectorBefore;
import com.movte.slate.domain.movie.domain.MovieBefore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieBeforeRepository extends JpaRepository<MovieBefore, String> {
    Director findByDirectorId(String directorId);
}
