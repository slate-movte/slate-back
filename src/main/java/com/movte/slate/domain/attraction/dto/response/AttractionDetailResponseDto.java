package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.domain.AttractionType;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttractionDetailResponseDto {

    // 공통 타입
    private Long id;
    private String title;
    private String tel;
    private List<String> images;
    private String homepage;
    private String overview;
    private AttractionType type;
    private LocationResponseDto location;


    // 관광지 추가 타입
    private String openTime;
    private String restDate;

    @Builder
    private AttractionDetailResponseDto(Long id, String title, String tel, List<String> images,
        String homepage, String overview, AttractionType type, LocationResponseDto location,
        String openTime, String restDate) {
        this.id = id;
        this.title = title;
        this.tel = tel;
        this.images = images;
        this.homepage = homepage;
        this.overview = overview;
        this.type = type;
        this.location = location;
        this.openTime = openTime;
        this.restDate = restDate;
    }

    public static AttractionDetailResponseDto from(Attraction attraction) {
        if (!attraction.getType().equals(AttractionType.ATTRACTION)) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_MATCH_ATTRACTION);
        }

        List<String> images = new ArrayList<>();
        if (attraction.getFirstImage().length() > 1) {
            images.add(attraction.getFirstImage());
        }
        if (attraction.getSecondImage().length() > 1) {
            images.add(attraction.getSecondImage());
        }

        return AttractionDetailResponseDto.builder()
            .id(attraction.getId())
            .title(attraction.getTitle())
            .tel(attraction.getTel())
            .images(images)
            .homepage(attraction.getHomepage())
            .overview(attraction.getOverview())
            .type(attraction.getType())
            .location(LocationResponseDto.from(attraction.getAddress()))
            .openTime(attraction.getOpenTime())
            .restDate(attraction.getRestDate())
            .build();
    }
}
