package com.movte.slate.domain.user.domain;


import java.time.LocalTime;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_unique",
                        columnNames = {"oauth_id", "oauth_provider"}
                )
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(name = "oauth_id", length = 512) // 임의로 길이 설정함 (ERD에는 길이 정의되어 있지 않음)
    private String oauthId;

    @Column(name = "oauth_provider")
    @Enumerated(value = EnumType.STRING)
    private OauthProvider oauthProvider;

    @Column(length = 512)
    private String profileImageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @Enumerated(value = EnumType.STRING)
    private UserState userState;

    // refreshtoken (DB 부하 위험)
    private String refreshToken;

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImageUrl(String profile_image_url) {
        this.profileImageUrl = profile_image_url;
    }

    public void setUserSate(UserState approved) {
        this.userState = approved;
    }

    public void setCreatedAt(LocalDateTime now) {
        this.createdAt = now;
    }

    public void setUpdatedAt(LocalDateTime now) {
        this.updatedAt = now;
    }
}
