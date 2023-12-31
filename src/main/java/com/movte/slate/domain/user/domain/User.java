package com.movte.slate.domain.user.domain;


import com.movte.slate.domain.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

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
public class User extends BaseTimeEntity {
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
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private UserState userState = UserState.APPROVED;

    public boolean isPending() {
        return UserState.PENDING.equals(userState);
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
