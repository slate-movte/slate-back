package com.movte.slate.domain.snapshot.application.service.response;


import com.movte.slate.domain.attraction.dto.response.LocationResponseDto;
import com.movte.slate.domain.snapshot.domain.Scene;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
 *  씬 상세 설명은 데이터 없는 관계로 제거했습니다.
 */

@Getter
@NoArgsConstructor
public class SceneDetailResponseDto {

    private Long sceneId;
    private String imageUrl;

    private String sceneLocation;
    private LocationResponseDto location;
    private SceneDetailMovieTitleResponse movie;

    @Builder
    public SceneDetailResponseDto(Long sceneId, String imageUrl, String sceneLocation,
        LocationResponseDto location, SceneDetailMovieTitleResponse movie) {
        this.sceneId = sceneId;
        this.imageUrl = imageUrl;
        this.sceneLocation = sceneLocation;
        this.location = location;
        this.movie = movie;
    }




    public static SceneDetailResponseDto from(Scene scene) {
        return SceneDetailResponseDto.builder()
            .sceneId(scene.getSceneId())
            .imageUrl(scene.getImageUrl())
            .location(LocationResponseDto.from(scene.getFilmLocation().getAddress()))
            .sceneLocation(scene.getSceneLocation())
            .movie(SceneDetailMovieTitleResponse.from(scene.getMovie()))
            .build();
    }
}
