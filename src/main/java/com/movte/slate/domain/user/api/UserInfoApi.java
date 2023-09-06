package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.service.EditUserInfoService;
import com.movte.slate.domain.user.application.service.GetUserInfoService;
import com.movte.slate.domain.user.application.service.dto.request.EditUserInfoServiceRequest;
import com.movte.slate.domain.user.application.service.dto.response.UserInfoGetResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserInfoApi {

    private final EditUserInfoService editUserInfoService;
    private final GetUserInfoService getUserInfoService;

    @GetMapping("/user/info")
    public ResponseEntity<SuccessResponse<UserInfoGetResponse>> userInfo(HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        Long userId = accessToken.getUserId();
        UserInfoGetResponse response = getUserInfoService.getUserInfo(userId);
        return ResponseFactory.success(response);
    }

    @PatchMapping(value = "/user/info")
    public ResponseEntity<SuccessResponse<String>> editUserInfo(@RequestParam("files") List<MultipartFile> files, @RequestParam("nickname") String nickname, HttpServletRequest servletRequest) {
        JwtToken accessToken = (JwtToken) servletRequest.getAttribute("accessToken");
        Long userId = accessToken.getUserId();
        editUserInfoService.editUserInfo(userId, new EditUserInfoServiceRequest(nickname, files.get(0)));
        return ResponseFactory.successWithoutData("회원정보 수정을 성공했습니다.");
    }
}
