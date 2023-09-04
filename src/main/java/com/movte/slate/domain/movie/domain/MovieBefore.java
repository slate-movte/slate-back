package com.movte.slate.domain.movie.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "movie_before")
public class MovieBefore {

    @Id
    private String movieId;

    private String title; //여기 null값임

    private String company;

    @Column(length = 5000)
    private String plot;

    private Integer openYear;

    private String openDate;

    private String rating;

    @Column(length = 5000)
    private String posterUrl;

    private String audienceCount;

    private String directorId;

    @Builder
    public MovieBefore(String movieId, String title, String company, String plot, Integer openYear, String openDate, String rating, String posterUrl, String audienceCount, String directorId) {
        this.movieId = movieId;
        this.title = title;
        this.company = company;
        this.plot = plot;
        this.openYear = openYear;
        this.openDate = openDate;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.audienceCount = audienceCount;
        this.directorId = directorId;
    }
}
