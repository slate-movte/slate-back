package com.movte.slate.domain.user.api;


import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;
import com.movte.slate.domain.user.application.usecase.LoginForDevelopUseCase;
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
    private final LoginForDevelopUseCase loginForDevelopUseCase;

    @GetMapping("/helper/login")
    private ResponseEntity<?> devLogin(@RequestParam("oauthId") String oauthId) {
        if (!isLocalProfiles(environment.getActiveProfiles())) {
            return ResponseFactory.fail(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }
        LoginServiceResponse response = loginForDevelopUseCase.login(oauthId);
        return ResponseFactory.success(response);
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
