package com.movte.slate.community.domain;

import com.movte.slate.domain.user.domain.User;

import javax.persistence.*;

@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    @ManyToOne
    private User writer;

}
