package com.movte.slate.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Entity
@Table(name="director_before")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DirectorBefore {

    @Id
    private String directorId;

    private String name;

    @Builder
    public DirectorBefore(String name) {
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
