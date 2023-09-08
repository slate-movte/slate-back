package com.movte.slate.domain.stillcut.application.service;

import com.movte.slate.domain.movie.application.service.dto.MovieResponseDto;
import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.snapshot.application.service.dto.SnapShotResponseDto;
import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.snapshot.repository.FindSceneByMoviePort;
import com.movte.slate.domain.stillcut.application.service.response.SearchMovieTitleServiceResponse;
import com.movte.slate.domain.movie.repository.FindMovieByTitlePort;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchBunchOfSnapshotWithMovieTitleService {
    private final FindMovieByTitlePort findMovieByTitlePort;
    private final FindSceneByMoviePort findSceneByMoviePort;

    public SearchMovieTitleServiceResponse searchBunchOfSnapshotWithMovieTitle(String title) {
        List<Movie> movies = findMovieByTitlePort.findByTitle(title);
        // 영화 데이터 한 개씩 뽑아서 response에 넣기
        List<MovieResponseDto> movieResponseDtos = new ArrayList<>();
        for(Movie movie : movies){
            // 영화 아이디로 씬들 찾기
            List<Scene> scenes = findSceneByMoviePort.findByMovie(movie);
            List<SnapShotResponseDto> snapShotResponseDtos = new ArrayList<>();
            for(Scene scene : scenes){
                SnapShotResponseDto snapShotResponseDto = SnapShotResponseDto.builder()
                        .scenId(scene.getSceneId())
                        .imageUrl(scene.getImageUrl())
                        .build();
                snapShotResponseDtos.add(snapShotResponseDto);
            }
            MovieResponseDto movieResponseDto = new MovieResponseDto().builder()
                    .id(movie.getMovieId())
                    .title(movie.getTitle())
                    .scenes(snapShotResponseDtos)
                    .build();
            movieResponseDtos.add(movieResponseDto);
        }
        return SearchMovieTitleServiceResponse.builder().movies(movieResponseDtos).build();
    }
}
