package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.service.UserService;
import com.movte.slate.domain.user.application.service.dto.UserDto;
import com.movte.slate.oidc.TokenStringExtractor;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserInfoApi {
    private final TokenStringExtractor tokenStringExtractor;
    private final UserService userService;

    @ResponseBody
    @GetMapping("/user/info")
    public ResponseEntity<Map<String, Object>> userInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> responseData = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            UserDto userDto = userService.userInfo(tokenStringExtractor.extractTokenString(request.getHeader("Authorization")));
            data.put("nickname",userDto.getNickname());
            data.put("profileImageUrl",userDto.getProfileImageUrl());
        }catch (ExpiredJwtException expiredJwtException){
            // 토큰이 만료된 경우
            responseData.put("data",data);
            responseData.put("message","권한이 없습니다.");
        }
        responseData.put("data",data);
        responseData.put("message","요청에 성공하였습니다.");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/user/info")
    public ResponseEntity<Map<String, Object>> userInfoEdit(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> responseData = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            UserDto userDto = userService.userInfoEdit(tokenStringExtractor.extractTokenString(request.getHeader("Authorization")),(String) requestBody.get("nickname"), (String) requestBody.get("profileImageUrl"));
            data.put("nickname",userDto.getNickname());
            data.put("profileImageUrl",userDto.getProfileImageUrl());
        }catch (ExpiredJwtException expiredJwtException){
            // 토큰이 만료된 경우
            responseData.put("data",data);
            responseData.put("message","권한이 없습니다.");
        }
        responseData.put("data",data);
        responseData.put("message","요청에 성공하였습니다.");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
