package com.movte.slate.community.api;

import com.movte.slate.community.application.response.ViewotherUserProfileServiceResponse;
import com.movte.slate.community.application.usecase.ViewOtherUserProfileUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ViewOtherUserProfileApi {
    private final JwtTokenFactory jwtTokenFactory;
    private final ViewOtherUserProfileUseCase viewOtherUserProfileUseCase;

    @GetMapping("/user/{other_user_id}/profile")
    public ResponseEntity<SuccessResponse<ViewotherUserProfileServiceResponse>> viewOtherUserProfile(@PathVariable("other_user_id") long otherUserId) {
        ViewotherUserProfileServiceResponse response = viewOtherUserProfileUseCase.viewOtherUserProfile(otherUserId);
        return ResponseFactory.success(response);
    }

}
