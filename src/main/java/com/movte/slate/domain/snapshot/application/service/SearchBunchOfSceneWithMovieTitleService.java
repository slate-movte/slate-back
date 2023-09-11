package com.movte.slate.domain.snapshot.application.service;

import com.movte.slate.domain.movie.application.service.dto.MovieResponseDto;
import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.repository.FindMovieByTitlePort;
import com.movte.slate.domain.snapshot.application.port.FindSceneByMoviePort;
import com.movte.slate.domain.snapshot.application.service.response.SceneResponseDto;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSceneWithMovieTitleServiceResponse;
import com.movte.slate.domain.snapshot.domain.Scene;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchBunchOfSceneWithMovieTitleService {
    private final FindMovieByTitlePort findMovieByTitlePort;
    private final FindSceneByMoviePort findSceneByMoviePort;

    public SearchBunchOfSceneWithMovieTitleServiceResponse searchBunchOfSceneWithMovieTitle(String title) {
        List<Movie> movies = findMovieByTitlePort.findByTitle(title);
        // 영화 데이터 한 개씩 뽑아서 response에 넣기
        List<MovieResponseDto> movieResponseDtos = new ArrayList<>();
        for (Movie movie : movies) {
            // 영화 아이디로 씬들 찾기
            List<Scene> scenes = findSceneByMoviePort.findByMovie(movie);
            List<SceneResponseDto> sceneResponseDtos = new ArrayList<>();
            for (Scene scene : scenes) {
                SceneResponseDto sceneResponseDto = SceneResponseDto.builder()
                        .sceneId(scene.getSceneId())
                        .imageUrl(scene.getImageUrl())
                        .build();
                sceneResponseDtos.add(sceneResponseDto);
            }
            MovieResponseDto movieResponseDto = new MovieResponseDto().builder()
                    .id(movie.getMovieId())
                    .title(movie.getTitle())
                    .scenes(sceneResponseDtos)
                    .build();
            movieResponseDtos.add(movieResponseDto);
        }
        return SearchBunchOfSceneWithMovieTitleServiceResponse.builder().movies(movieResponseDtos).build();
    }
}
