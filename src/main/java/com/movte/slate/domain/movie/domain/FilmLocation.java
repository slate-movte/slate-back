package com.movte.slate.domain.movie.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class FilmLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmLocationId;

    private String address;

    private Double latitude;

    private Double longitude;

    private String province;

    private String city;
    private String country;
    private String district;

    @Builder
    public FilmLocation(String address, Double latitude, Double longitude, String province,
                        String city, String country, String district) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.province = province;
        this.city = city;
        this.country = country;
        this.district = district;
    }
}
