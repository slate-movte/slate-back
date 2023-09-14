package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.dto.LocationTypeDto;
import com.movte.slate.domain.movie.domain.FilmLocation;
import com.movte.slate.domain.snapshot.domain.Scene;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapSearchResponseDto {

    private CommonInfo info;
    private LocationResponseDto location;


    public static MapSearchResponseDto from(Attraction attraction) {
        return new MapSearchResponseDto(new CommonInfo(attraction),
            LocationResponseDto.from(attraction.getAddress()));
    }

    public static MapSearchResponseDto from(Scene scene) {
        return new MapSearchResponseDto(new CommonInfo(scene),
            LocationResponseDto.from(scene.getFilmLocation().getAddress()));
    }

    /*
     *  외부 참조를 해제하기 위해 static class 로 작성.
     */

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CommonInfo {

        private Long id;
        private String type;
        private String title;

        public CommonInfo(Attraction attraction) {
            this.id = attraction.getId();
            this.type = attraction.getType().name();
            this.title = attraction.getTitle();
        }

        // TODO : 영화 촬영지도 넣어야 한다!
        // 데이터 없음.
        public CommonInfo(Scene scene) {
            this.id = scene.getSceneId();
            this.type = LocationTypeDto.MOVIE_LOCATION.name();
            this.title = "데이터 없음";
        }
    }
}
