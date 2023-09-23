package com.movte.slate.domain.snapshot.application.service;

import static java.util.Objects.requireNonNull;

import com.movte.slate.domain.attraction.domain.Address;
import com.movte.slate.domain.movie.domain.FilmLocation;
import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.movie.repository.FindMovieByTitlePort;
import com.movte.slate.domain.snapshot.application.service.request.InsertSceneServiceRequest;
import com.movte.slate.domain.snapshot.application.service.response.InsertSceneServiceResponse;
import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.snapshot.repository.SaveFilmLocationPort;
import com.movte.slate.domain.snapshot.repository.SaveImagePort;
import com.movte.slate.domain.snapshot.repository.SaveScenePort;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SceneService {
    private final SaveFilmLocationPort saveFilmLocationPort;
    private final FindMovieByTitlePort findMovieByTitlePort;
    private final SaveImagePort saveImagePort;
    private final SaveScenePort saveScenePort;

    public InsertSceneServiceResponse insertScene(InsertSceneServiceRequest request){
        // 영화 장소 추가
        FilmLocation filmLocation = saveFilmLocationPort.save(new FilmLocation(Address.builder()
                .address(request.getAddress())
                .gugunCode(request.getGugunCode())
                .sidoCode(request.getSidoCode())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build()));
        // 영화 이름으로 검색하여 영화 찾아오기
        List<Movie> movie = findMovieByTitlePort.findByTitle(request.getMovieTitle());
        if(movie.isEmpty()){
            throw new BadRequestException(BadRequestExceptionCode.NO_MOVIE);
        }
        // Scene Image 저장
        requireNonNull(request.getImage());
        Optional<String> urlOpt = saveImagePort.saveScene(request.getImage(), movie.get(0).getMovieId());
        if(urlOpt.isEmpty()){
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
        String url = urlOpt.get();
        Scene scene = saveScenePort.save(Scene.builder()
                .filmLocation(filmLocation)
                .imageUrl(url)
                .movie(movie.get(0))
                .sceneLocation(request.getSceneLocation())
                .build());
        return new InsertSceneServiceResponse("씬 추가 완료");
    }
}
