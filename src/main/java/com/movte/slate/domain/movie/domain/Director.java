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

    private String name;

    @Builder
    public Director(String name) {
        this.name = name;
    }
}
