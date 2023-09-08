package com.movte.slate.community.api;

import com.movte.slate.community.api.request.ViewOtherUserFeedsApiRequest;
import com.movte.slate.community.application.response.ViewOtherUserFeedsServiceResponse;
import com.movte.slate.community.application.usecase.ViewOtherUserFeedsUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ViewOtherUserFeedsApi {
    private final ViewOtherUserFeedsUseCase viewOtherUserFeedsUseCase;

    @GetMapping("/user/{other_user_id}/feeds")
    public ResponseEntity<SuccessResponse<ViewOtherUserFeedsServiceResponse>> viewOtherUserFeeds(@PathVariable("other_user_id") long otherUserId, ViewOtherUserFeedsApiRequest request) {
        ViewOtherUserFeedsServiceResponse response = viewOtherUserFeedsUseCase.view(request.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
