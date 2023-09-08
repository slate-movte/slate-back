package com.movte.slate.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="movie_actor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieActorId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Column(name = "`cast`")
    private String cast;

    @Builder
    public MovieActor(Movie movie, Actor actor, String cast) {
        this.movie = movie;
        this.actor = actor;
        this.cast = cast;
    }
}