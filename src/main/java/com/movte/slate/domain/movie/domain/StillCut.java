package com.movte.slate.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class StillCut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stillCutId;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "scene_id")
    private Scene scene;
}
