package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.FilmLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FilmLocationJpaAdapter implements SaveFilmLocationPort{
    private final FilmLocationRepository filmLocationRepository;

    @Override
    public FilmLocation save(FilmLocation filmLocation) {
        return filmLocationRepository.save(filmLocation);
    }
}
