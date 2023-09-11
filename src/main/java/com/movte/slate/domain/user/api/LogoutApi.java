package com.movte.slate.domain.user.api;

import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.application.usecase.InvalidateUseCase;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LogoutApi {
    private final InvalidateUseCase invalidateUseCase;

    @PostMapping("/user/logout")
    public ResponseEntity<SuccessResponse<String>> logout(HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        invalidateUseCase.invalidate(accessToken);
        return ResponseFactory.successWithoutData("로그아웃 성공했습니다.");
    }
}
