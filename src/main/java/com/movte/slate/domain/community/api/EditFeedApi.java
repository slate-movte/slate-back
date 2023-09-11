package com.movte.slate.domain.community.api;

import com.movte.slate.domain.community.api.request.EditFeedApiRequest;
import com.movte.slate.domain.community.application.response.EditFeedServiceResponse;
import com.movte.slate.domain.community.application.usecase.EditFeedUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EditFeedApi {
    private final EditFeedUseCase editFeedUseCase;

    @PatchMapping("/feed/{feed-id}")
    public ResponseEntity<SuccessResponse<EditFeedServiceResponse>> editFeed(HttpServletRequest servletRequest, @RequestBody @Valid EditFeedApiRequest request) {
        Long userId = ((JwtToken) servletRequest.getAttribute("accessToken")).getUserId();
        EditFeedServiceResponse response = editFeedUseCase.edit(userId, request.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
