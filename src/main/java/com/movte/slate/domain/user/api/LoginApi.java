package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.api.dto.request.AccessTokenRefreshRequest;
import com.movte.slate.domain.user.api.dto.request.LoginRequest;
import com.movte.slate.domain.user.api.dto.request.SignupRequest;
import com.movte.slate.domain.user.application.service.UserService;
import com.movte.slate.domain.user.application.service.dto.response.AccessTokenRefreshResponse;
import com.movte.slate.domain.user.application.service.dto.response.LoginResponse;
import com.movte.slate.domain.user.application.service.dto.response.SignupResponse;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/user/reissue")
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
    @PostMapping("/user/signup")
    public ResponseEntity<SuccessResponse<SignupResponse>> signup(@RequestBody @Valid SignupRequest request) {
        SignupResponse response = userService.signupUser(request.toServiceRequest());
        return ResponseFactory.success(response);
    }

    /**
     * 로그아웃
     *
     * @param request access token을 담고 있는 servlet request
     * @return 로그아웃 성공 여부
     */
    @PostMapping("/user/logout")
    public ResponseEntity<SuccessResponse<String>> logout(HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        userService.logout(accessToken);
        return ResponseFactory.successWithoutData("로그아웃 성공했습니다.");
    }

    /**
     * 회원 탈퇴
     *
     * @param request access token을 담고 있는 servlet request
     * @return 회원 탈퇴 성공 여부
     */
    @GetMapping("/user/withdrawal")
    public ResponseEntity<SuccessResponse<String>> withdrawal(HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        userService.withdrawal(accessToken);
        return ResponseFactory.successWithoutData("회원 탈퇴 성공했습니다.");
    }

    /**
     * 닉네임 중복 체크
     *
     * @param nickname 닉네임
     * @return 닉네임 중복 여부
     */
    @GetMapping("/user/nickname/duplicate")
    public ResponseEntity<?> checkIfNicknameIsDuplicate(@RequestParam("nickname") String nickname) {
        boolean isDuplicate = userService.checkIfNicknameIsDuplicate(nickname);
        if (isDuplicate) {
            return ResponseFactory.fail(new BadRequestException(BadRequestExceptionCode.DUPLICATE_NICKNAME));
        }
        return ResponseFactory.successWithoutData("닉네임이 중복되지 않습니다.");
    }
}
