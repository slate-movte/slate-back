package com.movte.slate.domain.attraction.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "content_type_id")
    private Integer contentTypeId;

    @Column(nullable = false, unique = true, name = "content_id")
    private Long contentId;

    @Column(name = "first_image")
    private String firstImage;

    @Column(name = "second_image")
    private String secondImage;

    private String tel;

    private String homepage;


    @Column(columnDefinition = "text")
    private String overview;
    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private AttractionType type;

    // 음식점,
    @Column(name = "treat_menu")
    private String treatMenu;

    // 음식점,  관광지 용도
    @Column(name = "open_time")
    private String openTime;

    @Column(name = "rest_date")
    private String restDate;

    @Builder
    public Attraction(String title, Integer contentTypeId, Long contentId, String firstImage,
                      String secondImage, String tel, String homepage, String overview, Address address,
                      AttractionType type, String treatMenu, String openTime, String restDate) {
        this.title = title;
        this.contentTypeId = contentTypeId;
        this.contentId = contentId;
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.tel = tel;
        this.homepage = homepage;
        this.overview = overview;
        this.address = address;
        this.type = type;
        this.treatMenu = treatMenu;
        this.openTime = openTime;
        this.restDate = restDate;
    }
}
