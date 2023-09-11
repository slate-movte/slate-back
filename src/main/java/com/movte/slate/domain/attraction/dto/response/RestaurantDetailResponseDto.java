package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.domain.AttractionType;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDetailResponseDto {

    // 공통 타입
    private Long id;
    private String title;
    private String tel;
    private List<String> images;
    private String homepage;
    private String overview;
    private AttractionType type;
    private LocationResponseDto location;

    // 음식점 전용
    private List<String> menus;
    private String openTime;
    private String restDate;

    @Builder
    public RestaurantDetailResponseDto(Long id, String title, String tel, List<String> images,
        String homepage, String overview, AttractionType type, LocationResponseDto location,
        List<String> menus, String openTime, String restDate) {
        this.id = id;
        this.title = title;
        this.tel = tel;
        this.images = images;
        this.homepage = homepage;
        this.overview = overview;
        this.type = type;
        this.location = location;
        this.menus = menus;
        this.openTime = openTime;
        this.restDate = restDate;
    }


    public static RestaurantDetailResponseDto from(Attraction attraction) {
        if (!attraction.getType().equals(AttractionType.RESTAURANT)) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_MATCH_ATTRACTION);
        }
        List<String> images = new ArrayList<>();
        images.add(attraction.getFirstImage());
        images.add(attraction.getSecondImage());
        List<String> menuList = new ArrayList<>();
        String treatMenu = attraction.getTreatMenu();

        if (treatMenu != null) {
            menuList.addAll(Arrays.stream(treatMenu.split(",")).toList());
        }

        return RestaurantDetailResponseDto.builder()
            .id(attraction.getId())
            .title(attraction.getTitle())
            .tel(attraction.getTel())
            .images(images)
            .homepage(attraction.getHomepage())
            .overview(attraction.getOverview())
            .type(attraction.getType())
            .location(LocationResponseDto.from(attraction.getAddress()))
            .menus(menuList)
            .openTime(attraction.getOpenTime())
            .restDate(attraction.getRestDate())
            .build();

    }
}
