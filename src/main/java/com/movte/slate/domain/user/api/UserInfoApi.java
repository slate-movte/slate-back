package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.service.UserService;
import com.movte.slate.domain.user.application.service.dto.response.UserInfoGetResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserInfoApi {

    private final UserService userService;

    @GetMapping("/user/info")
    public ResponseEntity<SuccessResponse<UserInfoGetResponse>> userInfo(HttpServletRequest request) {
        String accessToken = request.getHeader("accessToken");
        UserInfoGetResponse response = userService.userInfo(accessToken);
        return ResponseFactory.success(response);
    }
}
