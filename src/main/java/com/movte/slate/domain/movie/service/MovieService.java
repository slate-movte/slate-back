package com.movte.slate.domain.movie.service;

import com.movte.slate.domain.movie.domain.Director;
import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.domain.MovieBefore;
import com.movte.slate.domain.movie.repository.DirectorRepository;
import com.movte.slate.domain.movie.repository.MovieBeforeRepository;
import com.movte.slate.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieBeforeRepository movieBeforeRepository;
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;



    public void run()  {
        try {
            List<MovieBefore> lists = movieBeforeRepository.findAll();

            for (MovieBefore movieBefore : lists) {
                //1. director를 이전 directorid로 찾는다
                Director director = directorRepository.findByDirectorIdBef(movieBefore.getDirectorId());

                //2. movie에 재가공해서 저장한다 이때 director도 연관관계 담아준다
                Movie movie = Movie.builder()
                        .movieIdBef(movieBefore.getMovieId())
                        .plot(movieBefore.getPlot())
                        .company(movieBefore.getCompany())
                        .title(movieBefore.getTitle())
                        .audienceCount(movieBefore.getAudienceCount())
                        .rating(movieBefore.getRating())
                        .openDate(movieBefore.getOpenDate())
                        .openYear(movieBefore.getOpenYear())
                        .director(director)
                        .posterUrl(movieBefore.getPosterUrl())
                        .build();
                movieRepository.save(movie);
            }
        } catch (Exception e) {
            log.info(e);
        }
    }
}
