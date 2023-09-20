package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.snapshot.domain.Scene;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SceneRepository extends JpaRepository<Scene, Long> {

    List<Scene> findByMovie(Movie movie);

    @Query(value =
        "select scene from Scene scene, FilmLocation filmLocation where " +
            " scene.filmLocation = filmLocation and " +
            "6371 * acos(cos(radians(:paramLatitude)) "
            + "*cos(radians(filmLocation.address.latitude)) "
            + "*cos(radians(filmLocation.address.longitude)-radians(:paramLongitude)) "
            + "+sin(radians(:paramLatitude))*sin(radians(filmLocation.address.latitude))) < :paramLength")
    List<Scene> selectSceneByGps(
        @Param(value = "paramLatitude") BigDecimal latitude,
        @Param(value = "paramLongitude") BigDecimal longitude,
        @Param(value = "paramLength") Double length
    );

}
