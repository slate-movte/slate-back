package com.movte.slate.domain.snapshot.domain;

import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snapshotId;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "scene_id")
    private Scene scene;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
