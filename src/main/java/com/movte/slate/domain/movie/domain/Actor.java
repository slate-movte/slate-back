package com.movte.slate.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    private String actorIdBef;

    @Column
    private String name;

//    @Builder
//    public Actor(String name) {
//        this.name = name;
//    }

    @Builder
    public Actor(String actorIdBef, String name) {
        this.actorIdBef = actorIdBef;
        this.name = name;
    }

    //필요한지 검증 필요
//    @OneToMany(mappedBy = "actor")
//    private List<MovieActor> movieActors;
}