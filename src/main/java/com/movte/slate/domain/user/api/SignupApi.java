package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.usecase.SignupUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.domain.user.api.request.SignupApiRequest;
import com.movte.slate.domain.user.application.service.response.SignupServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignupApi {
    private final SignupUseCase signupUseCase;

    @PostMapping("/user/signup")
    public ResponseEntity<SuccessResponse<SignupServiceResponse>> signup(@RequestBody @Valid SignupApiRequest request) {
        SignupServiceResponse response = signupUseCase.signup(request.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
