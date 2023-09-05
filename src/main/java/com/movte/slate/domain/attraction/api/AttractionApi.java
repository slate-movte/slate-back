package com.movte.slate.domain.attraction.api;

import com.movte.slate.domain.attraction.dto.request.MapSearchDetailRequestDto;
import com.movte.slate.domain.attraction.dto.request.MapSearchRequestDto;
import com.movte.slate.domain.attraction.dto.response.MapSearchResponseDto;
import com.movte.slate.domain.attraction.application.usecase.AttractionSearchUseCase;
import com.movte.slate.global.response.ResponseFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/map/search/detail")
    public ResponseEntity<?> mapSearchDetails(@Validated MapSearchDetailRequestDto requestDto) {
        return ResponseFactory.success(attractionSearchUseCase.searchMapAttractionList(requestDto));
    }

    @GetMapping("/search/restaurant/{id}")
    public ResponseEntity<?> searchRestaurant(@PathVariable("id") Long id) {
        return ResponseFactory.success(attractionSearchUseCase.searchDetailRestaurant(id));
    }

    @GetMapping("/search/accommodation/{id}")
    public ResponseEntity<?> searchAccommodation(@PathVariable("id") Long id) {
        return ResponseFactory.success(attractionSearchUseCase.searchDetailAccommodation(id));
    }

    @GetMapping("/search/attraction/{id}")
    public ResponseEntity<?> searchAttraction(@PathVariable("id") Long id) {
        return ResponseFactory.success(attractionSearchUseCase.searchDetailAttraction(id));
    }

}
