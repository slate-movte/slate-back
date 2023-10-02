package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.FilmLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmLocationRepository extends JpaRepository<FilmLocation, Long> {
}
