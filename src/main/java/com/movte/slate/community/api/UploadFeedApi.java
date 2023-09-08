package com.movte.slate.community.api;

import com.movte.slate.community.api.request.UploadFeedApiRequest;
import com.movte.slate.community.application.response.UploadFeedServiceResponse;
import com.movte.slate.community.application.usecase.UploadFeedUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.JwtTokenFactory;
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
    private final JwtTokenFactory jwtTokenFactory;
    private final UploadFeedUseCase uploadFeedUseCase;

    @PostMapping("/feed/new")
    public ResponseEntity<SuccessResponse<UploadFeedServiceResponse>> upload(@RequestBody @Valid UploadFeedApiRequest uploadFeedApiRequest,
                                                                             HttpServletRequest servletRequest) {
        long userId = jwtTokenFactory.create(servletRequest.getHeader("accessToken")).getUserId();
        UploadFeedServiceResponse response = uploadFeedUseCase.upload(userId, uploadFeedApiRequest.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
