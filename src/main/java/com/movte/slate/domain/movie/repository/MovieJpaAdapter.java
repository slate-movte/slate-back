package com.movte.slate.domain.movie.repository;

import com.movte.slate.domain.movie.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MovieJpaAdapter implements FindMovieByTitlePort {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findByTitle(String title){
        return movieRepository.findByTitleIsContaining(title);
    }
}
