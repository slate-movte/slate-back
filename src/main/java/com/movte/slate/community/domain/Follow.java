package com.movte.slate.community.domain;

import com.movte.slate.domain.user.domain.User;

import javax.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followee;
}
