package com.movte.slate.domain.user.application.service.dto;

import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.domain.UserState;
import lombok.Builder;
import lombok.Getter;


@Getter
public class UserDto {
    private long id;
    private String nickname;
    private String profileImageUrl;
    private UserState userState;

    @Builder
    public UserDto(long id, UserState userState, String nickname, String profileImageUrl) {
        this.id = id;
        this.userState = userState;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userState(user.getUserState())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .userState(userState)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
