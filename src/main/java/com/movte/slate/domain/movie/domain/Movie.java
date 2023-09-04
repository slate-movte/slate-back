package com.movte.slate.domain.movie.domain;


import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

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

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @Builder
    public Movie(String title, String company, String plot,
                 Integer openYear, String openDate, String rating, String posterUrl,
                 String audienceCount, Director director) {
        this.title = title;
        this.company = company;
        this.plot = plot;
        this.openYear = openYear;
        this.openDate = openDate;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.audienceCount = audienceCount;
        this.director = director;
    }
}
