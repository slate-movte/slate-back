package com.movte.slate.domain.attraction.api;

import com.movte.slate.domain.attraction.dto.MapSearchRequestDto;
import com.movte.slate.domain.attraction.dto.response.MapSearchResponseDto;
import com.movte.slate.domain.attraction.usecase.AttractionSearchUseCase;
import com.movte.slate.global.response.ResponseFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AttractionApi {

    private final AttractionSearchUseCase attractionSearchUseCase;

    @GetMapping("/map/search")
    public ResponseEntity<?> mapSearch(@Validated MapSearchRequestDto requestDto) {

        List<MapSearchResponseDto> result = attractionSearchUseCase.searchMap(
            requestDto);
        return ResponseFactory.success(result);
    }

}
