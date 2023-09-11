package com.movte.slate.domain.community.api;

import com.movte.slate.domain.community.api.request.UploadFeedApiRequest;
import com.movte.slate.domain.community.application.response.UploadFeedServiceResponse;
import com.movte.slate.domain.community.application.usecase.UploadFeedUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UploadFeedApi {
    private final UploadFeedUseCase uploadFeedUseCase;

    @PostMapping("/feed/new")
    public ResponseEntity<SuccessResponse<UploadFeedServiceResponse>> upload(@RequestBody @Valid UploadFeedApiRequest uploadFeedApiRequest, HttpServletRequest servletRequest) {
        Long userId = ((JwtToken) servletRequest.getAttribute("accessToken")).getUserId();
        UploadFeedServiceResponse response = uploadFeedUseCase.upload(userId, uploadFeedApiRequest.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
