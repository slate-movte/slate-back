package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.usecase.LoginUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.domain.user.api.request.LoginApiRequest;
import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginApi {
    private final LoginUseCase loginUseCase;

    @PostMapping("/user/login")
    public ResponseEntity<SuccessResponse<LoginServiceResponse>> login(@RequestBody @Valid LoginApiRequest request) {
        LoginServiceResponse response = loginUseCase.login(request.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
