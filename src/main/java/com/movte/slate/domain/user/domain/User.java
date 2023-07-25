package com.movte.slate.domain.user.domain;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
/*
* @Builder 패턴을 쓰기 위한 용도 (외부에서 AllArgsConstructor를 쓰지 못하게 하기 위해서 access level은 private으로 설정)
* */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(length = 512) // 임의로 길이 설정함 (ERD에는 길이 정의되어 있지 않음)
    private String oauthId;

    @Column(length = 256)
    private String oauthProvider;

    @Column(length = 512)
    private String profileImageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
