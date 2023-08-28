package com.movte.slate.domain.user.application.usecase;


import org.springframework.stereotype.Service;

@Service
public class LoginUsecase {

    public String loginKakaoOauth(String code) {
        return "Kakao Login";
    }
}
