package com.movte.slate.domain.auth.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApi {

    @GetMapping("/auth")
    public String getAuth() {
        return "auth";
    }
}
