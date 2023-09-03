package com.movte.slate.domain.movie.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@Builder
@Table(name="director_after")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String movieIdBefore;

    @OneToMany(mappedBy = "director")
    private List<Movie> movieList;
}
