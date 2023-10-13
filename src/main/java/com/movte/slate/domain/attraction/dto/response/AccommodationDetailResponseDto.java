package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.domain.AttractionType;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccommodationDetailResponseDto {

    // 공통 타입
    private Long id;
    private String title;
    private String tel;
    private List<String> images;
    private String homepage;
    private String overview;
    private AttractionType type;
    private LocationResponseDto location;

    @Builder
    private AccommodationDetailResponseDto(Long id, String title, String tel, List<String> images,
        String homepage, String overview, AttractionType type, LocationResponseDto location) {
        this.id = id;
        this.title = title;
        this.tel = tel;
        this.images = images;
        this.homepage = homepage;
        this.overview = overview;
        this.type = type;
        this.location = location;
    }

    public static AccommodationDetailResponseDto from(Attraction attraction) {
        if (!attraction.getType().equals(AttractionType.ACCOMMODATION)) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_MATCH_ATTRACTION);
        }
        List<String> images = new ArrayList<>();
        if (attraction.getFirstImage().length() > 1) {
            images.add(attraction.getFirstImage());
        }
        if (attraction.getSecondImage().length() > 1) {
            images.add(attraction.getSecondImage());
        }

        return AccommodationDetailResponseDto.builder()
            .id(attraction.getId())
            .title(attraction.getTitle())
            .tel(attraction.getTel())
            .images(images)
            .homepage(attraction.getHomepage())
            .overview(attraction.getOverview())
            .type(attraction.getType())
            .location(LocationResponseDto.from(attraction.getAddress()))
            .build();
    }
}
