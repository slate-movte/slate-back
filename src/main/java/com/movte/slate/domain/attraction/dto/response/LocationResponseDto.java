package com.movte.slate.domain.attraction.dto.response;


import com.movte.slate.domain.attraction.domain.Address;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 위치 정보를 나타내는 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class LocationResponseDto {

    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public static LocationResponseDto from(Address address) {
        return new LocationResponseDto(address.getAddress(), address.getLatitude(),
            address.getLongitude());
    }
}
