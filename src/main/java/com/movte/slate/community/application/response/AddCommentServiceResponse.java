package com.movte.slate.community.application.response;

import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;

import javax.persistence.Lob;

public class AddCommentServiceResponse {

    private Long id;
    private User writerId;
    private Feed feed;

    @Lob
    private String content;
}
