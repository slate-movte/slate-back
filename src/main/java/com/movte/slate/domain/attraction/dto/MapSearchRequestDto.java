package com.movte.slate.domain.attraction.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MapSearchRequestDto {

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

    @NotNull(message = "type 을 정확히 입력해주세요.")
    private LocationTypeDto locationType;

    @NotNull
    @Positive
    private Integer range;
}
