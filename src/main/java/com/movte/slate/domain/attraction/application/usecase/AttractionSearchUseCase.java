package com.movte.slate.domain.attraction.application.usecase;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.domain.AttractionType;
import com.movte.slate.domain.attraction.dto.request.MapSearchDetailRequestDto;
import com.movte.slate.domain.attraction.dto.request.MapSearchRequestDto;
import com.movte.slate.domain.attraction.dto.LocationTypeDto;
import com.movte.slate.domain.attraction.dto.response.AccommodationDetailResponseDto;
import com.movte.slate.domain.attraction.dto.response.AttractionDetailResponseDto;
import com.movte.slate.domain.attraction.dto.response.AttractionListResponseDto;
import com.movte.slate.domain.attraction.dto.response.MapSearchResponseDto;
import com.movte.slate.domain.attraction.dto.response.RestaurantDetailResponseDto;
import com.movte.slate.domain.attraction.repository.AttractionRepository;
import com.movte.slate.domain.snapshot.repository.SceneRepository;
import com.movte.slate.global.exception.NotFoundException;
import com.movte.slate.global.exception.NotFoundExceptionCode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttractionSearchUseCase {

    private final AttractionRepository attractionRepository;
    private final SceneRepository sceneRepository;

    public List<MapSearchResponseDto> searchMap(MapSearchRequestDto requestDto) {
        List<MapSearchResponseDto> result = new ArrayList<>();

        BigDecimal requestLongitude = new BigDecimal(requestDto.getLongitude());
        BigDecimal requestLatitude = new BigDecimal(requestDto.getLatitude());
        double range = Double.parseDouble(requestDto.getRange().toString());
        if (searchTargetIsAttraction(requestDto.getLocationType())) {
            AttractionType attractionType = requestDto.getLocationType().toAttractionType();
            List<MapSearchResponseDto> queryResult = attractionRepository.selectAttractionByTypeAndGps(
                    attractionType, requestLatitude, requestLongitude, range)
                .stream().map(MapSearchResponseDto::from)
                .toList();
            result.addAll(queryResult);
        } else {
            List<MapSearchResponseDto> queryResult = sceneRepository.selectSceneByGps(
                    requestLatitude,
                    requestLongitude, range)
                .stream().map(MapSearchResponseDto::from)
                .toList();
            result.addAll(queryResult);
        }
        return result;
    }

    private boolean searchTargetIsAttraction(LocationTypeDto type) {
        return !type.equals(LocationTypeDto.MOVIE_LOCATION);
    }

    public AttractionDetailResponseDto searchDetailAttraction(Long attractionId) {
        return AttractionDetailResponseDto.from(findAttraction(attractionId));
    }

    public AccommodationDetailResponseDto searchDetailAccommodation(Long attractionId) {
        return AccommodationDetailResponseDto.from(findAttraction(attractionId));
    }

    public RestaurantDetailResponseDto searchDetailRestaurant(Long attractionId) {
        return RestaurantDetailResponseDto.from(findAttraction(attractionId));
    }

    public List<AttractionListResponseDto> searchMapAttractionList(
        MapSearchDetailRequestDto requestDto) {
        return attractionRepository.findAllByIdIn(requestDto.getIds())
            .stream().map(AttractionListResponseDto::from).toList();
    }

    private Attraction findAttraction(Long id) {
        return attractionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(NotFoundExceptionCode.NOT_FOUND_ATTRACTION));
    }
}
