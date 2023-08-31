package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.UserDto;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.domain.UserState;
import com.movte.slate.domain.user.repository.UserRepository;
import com.movte.slate.oidc.IdTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // oauth provider(apple, kakao) 와 oauth id을 가지고서 DB에 이미 등록된 유저를 찾는다.
    // User가 존재하지 않으면 Optional.empty()를 리턴한다.
    public Optional<UserDto> findUser(String oauthId, OauthProvider oauthProvider) {
        Optional<User> user = userRepository.findByOauthIdAndOauthProvider(oauthId, oauthProvider);
        return user.map(UserDto::of);
    }

    // 새로운 유저 정보를 만들어서 DB에 등록한다.
    public UserDto signup(OauthProvider oAuthProvider, IdTokenDto idTokenDto) {
        // 영속성 엔티티로 변환해서 DB에 넘겨준다. DB에 저장한다.
        User user = User.builder()
                .oauthProvider(oAuthProvider)
                .oauthId(idTokenDto.getOauthId())
                .nickname(idTokenDto.getNickname())
                .userState(UserState.PENDING)
                .build();
        user = userRepository.save(user);
        return UserDto.of(user);
    }

    public boolean isSignOn(OauthProvider oauthProvider, String oauthId) {
        return userRepository.existsByOauthIdAndOauthProvider(oauthId, oauthProvider);
    }
}
