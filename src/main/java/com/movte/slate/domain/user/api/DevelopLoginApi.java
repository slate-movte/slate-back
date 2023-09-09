package com.movte.slate.domain.user.api;


import com.movte.slate.domain.user.application.service.DevelopLoginHelper;
import com.movte.slate.domain.user.application.service.dto.response.LoginResponse;
import com.movte.slate.global.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DevelopLoginApi {

    private final Environment environment;
    private final DevelopLoginHelper developLoginHelper;

    @GetMapping("/helper/login")
    private ResponseEntity<?> devLogin(@RequestParam("oauthId") String oauthId) {
        if (!isLocalProfiles(environment.getActiveProfiles())) {
            return ResponseFactory.fail(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }
        LoginResponse result = developLoginHelper.login(oauthId);
        return ResponseFactory.success(result);
    }


    private boolean isLocalProfiles(String[] profiles) {
        for (String profile : profiles) {
            if (!profile.equals("local")) {
                return false;
            }
        }
        return true;
    }


}
