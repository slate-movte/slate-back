package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.FilmLocation;

public interface SaveFilmLocationPort {
    FilmLocation save(FilmLocation filmLocation);
}
