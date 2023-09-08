package com.movte.slate.domain.attraction.dto.response;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.domain.AttractionType;
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
public class AttractionListResponseDto {

    private Long id;
    private String title;
    private String tel;
    private List<String> images;
    private AttractionType type;
    private LocationResponseDto location;

    // 음식점이 아니거나 menu 값이 없으면 빈 배열.
    private List<String> menus;

    @Builder
    public AttractionListResponseDto(Long id, String title, String tel, List<String> images,
        AttractionType type, LocationResponseDto location, List<String> menus) {
        this.id = id;
        this.title = title;
        this.tel = tel;
        this.images = images;
        this.type = type;
        this.location = location;
        this.menus = menus;
    }

    public static AttractionListResponseDto from(Attraction attraction) {
        List<String> menuList = new ArrayList<>();
        List<String> images = new ArrayList<>();
        images.add(attraction.getFirstImage());
        images.add(attraction.getSecondImage());
        String treatMenu = attraction.getTreatMenu();

        if (treatMenu != null) {
            menuList.addAll(Arrays.stream(treatMenu.split(",")).toList());
        }
        return AttractionListResponseDto.builder()
            .id(attraction.getId())
            .title(attraction.getTitle())
            .tel(attraction.getTel())
            .images(images)
            .type(attraction.getType())
            .location(LocationResponseDto.from(attraction.getAddress()))
            .menus(menuList)
            .build();
    }

}
