package com.movte.slate.domain.attraction.usecase;

import com.movte.slate.domain.attraction.domain.AttractionType;
import com.movte.slate.domain.attraction.dto.MapSearchRequestDto;
import com.movte.slate.domain.attraction.dto.LocationTypeDto;
import com.movte.slate.domain.attraction.dto.response.MapSearchResponseDto;
import com.movte.slate.domain.attraction.repository.AttractionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttractionSearchUseCase {

    private final AttractionRepository attractionRepository;


    @Transactional(readOnly = true)
    public List<MapSearchResponseDto> searchMap(MapSearchRequestDto requestDto) {
        List<MapSearchResponseDto> result = new ArrayList<>();

        if (searchTargetIsAttraction(requestDto.getLocationType())) {
            AttractionType attractionType = requestDto.getLocationType().toAttractionType();
            BigDecimal longitude = new BigDecimal(requestDto.getLongitude());
            BigDecimal latitude = new BigDecimal(requestDto.getLatitude());
            List<MapSearchResponseDto> queryResult = attractionRepository.selectAttractionByTypeAndGps(
                    attractionType, latitude, longitude,
                    Double.parseDouble(requestDto.getRange().toString()))
                .stream().map(MapSearchResponseDto::from)
                .toList();
            result.addAll(queryResult);
        }

        /*
         *  TODO : 영화 촬영지 기준으로 쿼리 짜야 함.
         */

        return result;
    }

    private boolean searchTargetIsAttraction(LocationTypeDto type) {
        return !type.equals(LocationTypeDto.MOVIE_LOCATION);
    }



}
