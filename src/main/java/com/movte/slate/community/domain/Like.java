package com.movte.slate.community.domain;

import com.movte.slate.domain.user.domain.User;

import javax.persistence.*;

@Entity(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Feed feed;
}
