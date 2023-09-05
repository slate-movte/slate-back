package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.attraction.domain.Attraction;
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
    }
}
