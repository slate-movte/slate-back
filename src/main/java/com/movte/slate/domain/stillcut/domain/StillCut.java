package com.movte.slate.domain.stillcut.domain;

import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StillCut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stillCutId;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "scene_id")
    private Scene scene;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
