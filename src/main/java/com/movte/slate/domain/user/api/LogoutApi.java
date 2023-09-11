package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.api.request.LogoutApiRequest;
import com.movte.slate.domain.user.application.port.PutAccessTokenToBlackListTokenPort;
import com.movte.slate.domain.user.application.port.PutRefreshTokenToBlackListTokenPort;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.application.usecase.CreateJwtTokenUseCase;
import com.movte.slate.jwt.application.usecase.ExtractTokenStringUseCase;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class LogoutApi {
    private final PutAccessTokenToBlackListTokenPort putAccessTokenToBlackListTokenPort;
    private final PutRefreshTokenToBlackListTokenPort putRefreshTokenToBlackListTokenPort;
    private final ExtractTokenStringUseCase extractTokenStringUseCase;
    private final CreateJwtTokenUseCase createJwtTokenUseCase;

    @PostMapping("/user/logout")
    public ResponseEntity<SuccessResponse<String>> logout(HttpServletRequest servletRequest, @RequestBody @Valid LogoutApiRequest request) {
        JwtToken accessToken = (JwtToken) servletRequest.getAttribute("accessToken");
        String authorization = servletRequest.getHeader("Authorization");
        String accessTokenValue = extractTokenStringUseCase.extractTokenString(authorization);
        putAccessTokenToBlackListTokenPort.blackList(accessTokenValue, accessToken.getExpiredAt(), new Date());
        String refreshTokenValue = request.getRefreshToken();
        JwtToken refreshToken = createJwtTokenUseCase.create(refreshTokenValue);
        putRefreshTokenToBlackListTokenPort.blackList(request.getRefreshToken(), refreshToken.getExpiredAt(), new Date());
        return ResponseFactory.successWithoutData("로그아웃 성공했습니다.");
    }
}
