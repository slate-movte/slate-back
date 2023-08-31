package com.movte.slate.domain.user.application.service.dto;

import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.domain.UserState;
import lombok.Builder;
import lombok.Getter;


@Getter
public class UserDto {
    private long id;
    private UserState userState;

    @Builder
    public UserDto(long id, UserState userState) {
        this.id = id;
        this.userState = userState;
    }

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userState(user.getUserState())
                .build();
    }
}
