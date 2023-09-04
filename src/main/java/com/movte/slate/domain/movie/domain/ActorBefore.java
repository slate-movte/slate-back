package com.movte.slate.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "actorBefore")
public class ActorBefore {

    @Id
    private String actorId;

    @Column
    private String name;

    @Builder
    public ActorBefore(String name) {
        this.name = name;
    }

    //필요한지 검증 필요
//    @OneToMany(mappedBy = "actor")
//    private List<MovieActor> movieActors;
}