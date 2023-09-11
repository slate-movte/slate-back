package com.movte.slate.domain.community.domain;

import com.movte.slate.domain.common.BaseTimeEntity;
import com.movte.slate.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "likes")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Feed feed;


}
