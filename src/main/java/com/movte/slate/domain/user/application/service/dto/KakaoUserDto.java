package com.movte.slate.domain.user.application.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoUserDto {
    String oauth_id;
    String o_auth_provider;
    String email;
    String nickname;
    String profile_img_url;
}
