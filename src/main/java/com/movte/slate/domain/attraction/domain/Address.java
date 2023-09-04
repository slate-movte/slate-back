package com.movte.slate.domain.attraction.domain;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Column(nullable = false)
    private String address;

    // 소수를 포함하는 전체 자릿수, precision : 소수 포함 자릿수, scale: 소수 자리
    @Column(nullable = false, precision = 13, scale = 10)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 13, scale = 10)
    private BigDecimal longitude;

    @Column(nullable = false, name = "sido_code")
    private String sidoCode;

    @Column(nullable = false, name = "gugun_code")
    private String gugunCode;


    @Builder
    public Address(String address, BigDecimal latitude, BigDecimal longitude, String sidoCode,
        String gugunCode) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
    }
}
