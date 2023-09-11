package com.movte.slate.community.api;

import com.movte.slate.community.api.request.AddCommentApiRequest;
import com.movte.slate.community.application.request.AddCommentServiceRequest;
import com.movte.slate.community.application.response.AddCommentServiceResponse;
import com.movte.slate.community.application.usecase.AddCommentUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.JwtTokenFactory;
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
    private final JwtTokenFactory jwtTokenFactory;

    @PostMapping("/feeds/{feed-id}/comments/new")
    public ResponseEntity<SuccessResponse<AddCommentServiceResponse>> addComment(@PathVariable("feed-id") long feedId, @RequestBody @Valid AddCommentApiRequest request, HttpServletRequest servletRequest) {
        Long userId = jwtTokenFactory.create(servletRequest.getHeader("accessToken")).getUserId();
        AddCommentServiceRequest serviceRequest = request.toServiceRequest();
        AddCommentServiceResponse response = addCommentUseCase.addComment(userId, feedId, serviceRequest);
        return ResponseFactory.success(response);
    }
}
