package com.movte.slate.domain.community.api;

import com.movte.slate.domain.community.application.response.ViewOtherUserProfileServiceResponse;
import com.movte.slate.domain.community.application.usecase.ViewOtherUserProfileUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ViewOtherUserProfileApi {
    private final ViewOtherUserProfileUseCase viewOtherUserProfileUseCase;

    @GetMapping("/user/{other_user_id}/profile")
    public ResponseEntity<SuccessResponse<ViewOtherUserProfileServiceResponse>> viewOtherUserProfile(@PathVariable("other_user_id") long otherUserId, HttpServletRequest servletRequest, @RequestParam("feed-count") int feedCount) {
        Long userId = ((JwtToken) servletRequest.getAttribute("accessToken")).getUserId();
        ViewOtherUserProfileServiceResponse response = viewOtherUserProfileUseCase.viewOtherUserProfile(userId, otherUserId, feedCount);
        return ResponseFactory.success(response);
    }


}