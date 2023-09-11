package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.api.request.RefreshAccessTokenApiRequest;
import com.movte.slate.domain.user.application.service.response.RefreshAccessTokenServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.application.usecase.ReissueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReissueApi {
    private final ReissueUseCase reissueUseCase;

    @PostMapping("/user/reissue")
    public ResponseEntity<SuccessResponse<RefreshAccessTokenServiceResponse>> refreshAccessToken(@RequestBody @Valid RefreshAccessTokenApiRequest request) {
        return ResponseFactory.success(reissueUseCase.reissue(request.toServiceRequest()));
    }
}
