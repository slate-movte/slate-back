package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.usecase.WithdrawalUseCase;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class WithdrawalApi {
    private final WithdrawalUseCase withdrawalUseCase;

    @GetMapping("/user/withdrawal")
    public ResponseEntity<SuccessResponse<String>> withdrawal(HttpServletRequest request) {
        Long userId = ((JwtToken) request.getAttribute("accessToken")).getUserId();
        withdrawalUseCase.withdrawal(userId);
        return ResponseFactory.successWithoutData("회원 탈퇴 성공했습니다.");
    }
}
