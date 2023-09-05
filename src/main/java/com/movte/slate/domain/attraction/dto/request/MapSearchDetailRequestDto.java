package com.movte.slate.domain.attraction.dto.request;


import com.movte.slate.domain.attraction.dto.LocationTypeDto;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapSearchDetailRequestDto {

    @NotNull
    private List<Long> ids;

}
