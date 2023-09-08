package com.movte.slate.community.api;

import com.movte.slate.community.application.usecase.CancelLikeUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CancelLikeFeedApi {
    private final CancelLikeUseCase cancelLikeUseCase;
    private final JwtTokenFactory jwtTokenFactory;

    @DeleteMapping("/feed/{liked_feed_id}/like")
    public ResponseEntity<SuccessResponse<String>> cancelLikeFeed(@PathVariable("liked_feed_id") long likedFeedId,
                                                                  HttpServletRequest servletRequest) {
        long userId = jwtTokenFactory.create(servletRequest.getHeader("accessToken")).getUserId();
        cancelLikeUseCase.cancel(userId, likedFeedId);
        return ResponseFactory.successWithoutData("게시글을 Like하는데 성공했습니다.");
    }

}
