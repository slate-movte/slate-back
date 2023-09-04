package com.movte.slate.domain.attraction.repository;

import com.movte.slate.domain.attraction.domain.Attraction;
import com.movte.slate.domain.attraction.domain.AttractionType;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    @Query(value =
        "select attr from Attraction attr where attr.type = :paramType and  " +
            "6371 * acos(cos(radians(:paramLatitude)) "
            + "*cos(radians(attr.address.latitude)) "
            + "*cos(radians(attr.address.longitude)-radians(:paramLongitude)) "
            + "+sin(radians(:paramLatitude))*sin(radians(attr.address.latitude))) < :paramLength")
    List<Attraction> selectAttractionByTypeAndGps(
        @Param(value = "paramType") AttractionType type,
        @Param(value = "paramLatitude") BigDecimal latitude,
        @Param(value = "paramLongitude") BigDecimal longitude,
        @Param(value = "paramLength") Double length
    );
}
