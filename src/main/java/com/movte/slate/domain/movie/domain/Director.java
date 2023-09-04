package com.movte.slate.domain.movie.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@Table(name="director")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directorId;

    private String directorIdBef;

    private String name;

    @Builder
    public Director(String directorIdBef, String name) {
        this.directorIdBef = directorIdBef;
        this.name = name;
    }

    //    @OneToMany(mappedBy = "director")
//    private List<Movie> movieList;
//
//    @Builder
//    public Director(String name, List<Movie> movieList) {
//        this.name = name;
//        this.movieList = movieList;
//    }
}
