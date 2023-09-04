package com.movte.slate.domain.attraction.dto;

import com.movte.slate.domain.attraction.domain.AttractionType;

// 입력값을 위한 LocationType.
public enum LocationTypeDto {
    RESTAURANT, ACCOMMODATION, MOVIE_LOCATION, ATTRACTION;

    public AttractionType toAttractionType() {
        return AttractionType.valueOf(this.name());
    }
}
