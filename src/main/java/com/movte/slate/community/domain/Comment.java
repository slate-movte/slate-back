package com.movte.slate.community.domain;

import com.movte.slate.domain.common.BaseTimeEntity;
import com.movte.slate.domain.user.domain.User;

import javax.persistence.*;

@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Feed feed;

    @Lob
    private String content;

}
