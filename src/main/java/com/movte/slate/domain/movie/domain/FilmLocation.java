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

    @Column(nullable = false, name = "sido_code")
    private String sidoCode;
    @Column(nullable = false, name = "gugun_code")
    private String gugunCode;

    @Builder
    public FilmLocation(String address, Double latitude, Double longitude,
                        String sidoCode, String gugunCode) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
    }
}
