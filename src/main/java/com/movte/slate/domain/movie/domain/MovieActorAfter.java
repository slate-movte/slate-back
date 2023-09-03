package com.movte.slate.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="movie_actor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieActorAfter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieActorId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actorId;

    private String cast;
}