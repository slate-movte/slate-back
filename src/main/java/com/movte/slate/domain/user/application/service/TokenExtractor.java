package com.movte.slate.domain.user.application.service;

public class TokenExtractor {
    public static String getUnsignedToken(String token) throws Exception {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) throw new Exception();
        return splitToken[0] + "." + splitToken[1] + "."; // 헤더, 페이로드 반환
    }
}
