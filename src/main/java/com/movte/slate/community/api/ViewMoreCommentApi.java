package com.movte.slate.community.api;

import com.movte.slate.community.application.response.ViewMoreCommentServiceResponse;
import com.movte.slate.community.application.usecase.ViewMoreCommentUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ViewMoreCommentApi {
    private final JwtTokenFactory jwtTokenFactory;
    private final ViewMoreCommentUseCase viewMoreCommentUseCase;

    @GetMapping("/feed/{comments_feed_id}/comments")
    public ResponseEntity<SuccessResponse<ViewMoreCommentServiceResponse>> getMoreComments(@PathVariable("comments_feed_id") long commentsFeedId, HttpServletRequest servletRequest) {
        long userId = jwtTokenFactory.create(servletRequest.getHeader("accessToken")).getUserId();
        ViewMoreCommentServiceResponse response = viewMoreCommentUseCase.viewMoreComments(userId, commentsFeedId);
        return ResponseFactory.success(response);
    }
}
