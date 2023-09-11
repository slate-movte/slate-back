package com.movte.slate.domain.community.domain;

import com.movte.slate.domain.common.BaseTimeEntity;
import com.movte.slate.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
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
