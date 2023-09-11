package com.movte.slate.domain.community.api;

import com.movte.slate.domain.community.api.request.AddCommentApiRequest;
import com.movte.slate.domain.community.application.request.AddCommentServiceRequest;
import com.movte.slate.domain.community.application.response.AddCommentServiceResponse;
import com.movte.slate.domain.community.application.usecase.AddCommentUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AddCommentApi {
    private final AddCommentUseCase addCommentUseCase;

    @PostMapping("/feeds/{feed-id}/comments/new")
    public ResponseEntity<SuccessResponse<AddCommentServiceResponse>> addComment(@PathVariable("feed-id") long feedId, @RequestBody @Valid AddCommentApiRequest request, HttpServletRequest servletRequest) {
        Long userId = ((JwtToken) servletRequest.getAttribute("accessToken")).getUserId();
        AddCommentServiceRequest serviceRequest = request.toServiceRequest();
        AddCommentServiceResponse response = addCommentUseCase.addComment(userId, feedId, serviceRequest);
        return ResponseFactory.success(response);
    }
}
