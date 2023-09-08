package com.movte.slate.community.domain;

import com.movte.slate.domain.common.BaseTimeEntity;
import com.movte.slate.domain.stillcut.domain.StillCut;
import com.movte.slate.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    @ManyToOne
    private User writer;
    @OneToOne
    private StillCut stillCut;

    public Long getId() {
        return id;
    }

    public void setSnapshot(StillCut stillCut) {
        this.stillCut = stillCut;
    }
}
