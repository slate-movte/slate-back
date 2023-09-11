package com.movte.slate.domain.community.domain;

import com.movte.slate.domain.common.BaseTimeEntity;
import com.movte.slate.domain.user.domain.User;

import javax.persistence.*;

@Entity
public class Follow extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followee;
}
