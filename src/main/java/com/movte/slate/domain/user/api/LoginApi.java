package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.service.KakaoService;
import com.movte.slate.domain.user.application.service.UserService;
import com.movte.slate.domain.user.application.service.dto.UserDto;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.oidc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class LoginApi {

    public static final String IS_SIGN_UP = "is_sign_up";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private final UserService userService;
    private final KakaoService kakaoService;
    private final JwtTokenIssuer jwtTokenIssuer;
    private final JwtConfigProperties jwtConfigProperties;
    private final KakaoConfigProperties kakaoConfigProperties;


    /*
    1. 클라이언트는 카카오 로그인 버튼을 클릭한다.
    2. 서비스는 카카오 인증 서버로 인가 코드 발급을 요청한다.
    3. 카카오 인증 서버는 클라이언트에게 카카오 계정 로그인을 요청한다.
    4. 클라이언트는 카카오 계정 로그인하고, 자원에 대한 권한을 승인한다.
    5. 카카오 인증 서버는 인가 코드를 만들어 서비스에게 준다.
        - 미리 등록한 Redirect URL로 인가 코드를 보내준다.
    6. 서비스는 인가코드를 통해 Access Token과 Refresh Token, 그리고 ID Token을 받는다.
     */
    @ResponseBody
    @GetMapping("/oidc/kakao")
    public void kakaoLogin(@RequestParam String code, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        /*
        1. 인가 코드를 가지고 ID Token을 얻는다.
        2. Access Token과 Refresh Token을 담은 Redirection URL을 카카오에게 전달한다.
        3. Redirection URL로 클라이언트는 리다이렉트되고, 그 결과 Access Token과 Refresh Token을 얻는다.
          - 회원이 아닌 유저라면 어떻게 되는가? Access Token와 Refresh Token을 발급한다.
          - Access Token Body 안에 회원 상태 정보가 담긴다.
         */
        IdTokenDto idToken = kakaoService.getIdToken(code);
        String oauthId = idToken.getOauthId();
        Optional<UserDto> user = userService.findUser(oauthId, OauthProvider.KAKAO);
        UserDto userDto = user.orElseGet(() -> userService.signup(OauthProvider.KAKAO, idToken));
        String accessToken = jwtTokenIssuer.createAccessToken(userDto);
        String refreshToken = jwtTokenIssuer.createRefreshToken(userDto);
        userService.saveRefreshToken(userDto.getId(), refreshToken);
        TokenResponseDTO token = new TokenResponseDTO(accessToken, refreshToken);
        setTokenRedirectAttributes(redirectAttributes, token);
        response.sendRedirect(makeTokenRedriectURL(token));
    }

    /* 카카오로부터 OIDC Public Key 를 가져온다.
     Redis에 캐싱한다.*/
    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCacheManager")
    @GetMapping("/oidc/kakao/openkeys")
    public String getOpenKeys() {
        return kakaoService.retrieveKakaoOpenKeysFromKakao();
    }

    private void setTokenRedirectAttributes(RedirectAttributes redirectAttributes, TokenResponseDTO token) {
        redirectAttributes.addAttribute(ACCESS_TOKEN, token.getAccess_token());
        redirectAttributes.addAttribute(REFRESH_TOKEN, token.getRefresh_token());
    }

    private String makeTokenRedriectURL(TokenResponseDTO token) {
        String tokenUrl = jwtConfigProperties.getTokenRedirectUrl();
        return tokenUrl + "?access_token=" + token.getAccess_token() + "&refresh_token=" + token.getRefresh_token();
    }


    /*
    refresh toekn 재발급용 API
     */
    @ResponseBody
    @GetMapping("/user/reissue")
    public String refreshAccessToken(@RequestBody Map<String, Object> refreshToken, HttpServletRequest request, HttpServletResponse response) throws IOException{
        /*
        1. refresh token이 만료되었는지 확인한다.
        2. refresh token이 만료되었으면 refresh token이 만료되었다는 정보를 반환한다.
        3. refresh token이 만료되지 않았으면 access token을 재발급한다.
        4. access token을 발급하고 반환한다.
         */
        System.out.println(request.getHeader("accessToken"));
        System.out.println(refreshToken.get("refreshToken"));
        return userService.refreshAccessToken(request.getHeader("accessToken"),(String) refreshToken.get("refreshToken"));
    }
}
