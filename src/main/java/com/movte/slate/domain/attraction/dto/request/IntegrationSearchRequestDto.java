package com.movte.slate.domain.attraction.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationSearchRequestDto {

    private String keyword;

    @NotNull
    private Long movieLastId;

    @NotNull
    private Long attractionLastId;
}
