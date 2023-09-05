package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.api.dto.request.UserInfoEditRequest;
import com.movte.slate.domain.user.application.service.UserService;
import com.movte.slate.domain.user.application.service.dto.response.UserInfoGetResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserInfoApi {

    private final UserService userService;

    @GetMapping("/user/info")
    public ResponseEntity<SuccessResponse<UserInfoGetResponse>> userInfo(HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        UserInfoGetResponse response = userService.userInfo(accessToken);
        return ResponseFactory.success(response);
    }

    // 파일을 받아야 한다.
    @PatchMapping(value = "/user/info", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<SuccessResponse<String>> editUserInfo(@RequestPart @Valid UserInfoEditRequest request, @RequestPart List<MultipartFile> files, HttpServletRequest servletRequest) {
        // S3 에 접속
        JwtToken accessToken = (JwtToken) servletRequest.getAttribute("accessToken");
        userService.editUserInfo(accessToken);
        return ResponseFactory.successWithoutData("회원정보 수정을 성공했습니다.");
    }
}
