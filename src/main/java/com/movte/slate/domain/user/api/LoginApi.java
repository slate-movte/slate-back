package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.service.KakaoService;
import com.movte.slate.domain.user.application.service.LoginService;
import com.movte.slate.domain.user.application.usecase.LoginUsecase;
import com.movte.slate.domain.user.dto.TokenReponseDTO;
import com.movte.slate.domain.user.dto.TokenResponseDTO;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RestController
public class LoginApi {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";

    private final LoginService loginService;
    private final KakaoService kakaoService;

    @Value("${login.token-redirect-url}")
    private String TOKEN_REDIRECT_URL;

    @ResponseBody
    @GetMapping("/oidc/kakao")
    public void kakaoLogin(@RequestParam String code, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        TokenReponseDTO token = loginService.KakaoLogin(code);
        setTokenRedirectAttributes(redirectAttributes, token);
        response.sendRedirect(makeTokenRedriectURL(token));
    }

    private static void setTokenRedirectAttributes(RedirectAttributes redirectAttributes, TokenResponseDTO token){
        redirectAttributes.addAllAttributes(ACCESS_TOKEN, token.getAccess_token());
        redirectAttributes.addAllAttributes(REFRESH_TOKEN, token.getRefresh_token());
    }

    private String makeTokenRedriectURL(TokenReponseDTO token) {
        return TOKEN_REDIRECT_URL + "/login/kakao/?access_token" + tokenResponseDto.getAccess_token() + "&refesh_token=" + tokenResponseDto.getRefresh_token();
    }

    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCachemanageer")
    @GetMapping("/oidc/kakao/openkeys")
    public String getOpenKeys() {
        return kakaoService.getOpenKeysFromKakaoOIDC();
    }
}
