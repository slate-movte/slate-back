package com.movte.slate.domain.movie.application.usecase;


import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.dto.MovieDetailResponseDto;
import com.movte.slate.domain.movie.repository.MovieRepository;
import com.movte.slate.global.exception.NotFoundException;
import com.movte.slate.global.exception.NotFoundExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieSearchUseCase {

    private final MovieRepository movieRepository;

    public MovieDetailResponseDto searchDetail(Long movieId) {
        Movie movie = findMovie(movieId);
        return MovieDetailResponseDto.from(movie);
    }

    private Movie findMovie(Long movieId) {
        Movie movie = movieRepository.selectMovieWithActorAndScene(movieId);
        if (movie == null) {
            throw new NotFoundException(NotFoundExceptionCode.NOT_FOUND_MOVIE);
        }
        return movie;
    }
}
