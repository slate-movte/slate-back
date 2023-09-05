package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.api.dto.request.AccessTokenRefreshRequest;
import com.movte.slate.domain.user.api.dto.request.LoginRequest;
import com.movte.slate.domain.user.api.dto.request.SignupRequest;
import com.movte.slate.domain.user.application.service.UserService;
import com.movte.slate.domain.user.application.service.dto.response.AccessTokenRefreshResponse;
import com.movte.slate.domain.user.application.service.dto.response.LoginResponse;
import com.movte.slate.domain.user.application.service.dto.response.SignupResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class LoginApi {
    private final UserService userService;


    @PostMapping("/user/login")
    public ResponseEntity<SuccessResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = userService.login(request.toServiceRequest());
        return ResponseFactory.success(response);
    }


    /*
    refresh toekn 재발급용 API
     */
    @GetMapping("/user/reissue")
    public ResponseEntity<SuccessResponse<AccessTokenRefreshResponse>> refreshAccessToken(@RequestBody @Valid AccessTokenRefreshRequest request) {
        /*
        1. refresh token이 만료되었는지 확인한다.
        2. refresh token이 만료되었으면 refresh token이 만료되었다는 정보를 반환한다.
        3. refresh token이 만료되지 않았으면 access token을 재발급한다.
        4. access token을 발급하고 반환한다.
         */
        return ResponseFactory.success(userService.refreshAccessToken(request.toServiceRequest()));
    }

    /**
     * 회원 가입
     *
     * @param request 회원 가입에 필요한 정보 (id token, nickname, profile image url)
     * @return 토큰
     */
    @GetMapping("/user/signup")
    public ResponseEntity<SuccessResponse<SignupResponse>> signup(@RequestBody @Valid SignupRequest request) {
        SignupResponse response = userService.signupUser(request.toServiceRequest());
        return ResponseFactory.success(response);
    }
}
