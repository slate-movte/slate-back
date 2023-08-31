package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.UserDto;
import com.movte.slate.domain.user.domain.OAuthProvider;
import com.movte.slate.domain.user.domain.User;
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

    // 인가 코드로 ID Token 가져와서 디코딩. 디코딩한 값 리턴.
    // oauth provider(apple, kakao) 와 id token을 가지고서 DB에 이미 등록된 유저를 찾는다.
    // User가 존재하지 않으면 Optional.empty()를 리턴한다.
    public Optional<UserDto> findUser(OAuthProvider oAuthProvider, IdTokenDto idTokenDTO) {
        Optional<User> user = userRepository.findByOauthIdAndOauthProvider(idTokenDTO.getOauthId(), oAuthProvider);
        return user.map(UserDto::of);
    }

    // 새로운 유저 정보를 만들어서 DB에 등록한다.
    public long signup(OAuthProvider oAuthProvider, IdTokenDto idTokenDto) {
        // 영속성 엔티티로 변환해서 DB에 넘겨준다. DB에 저장한다.
        User user = User.builder()
                .oauthProvider(oAuthProvider)
                .oauthId(idTokenDto.getOauthId())
                .nickname(idTokenDto.getNickname())
                .build();
        user = userRepository.save(user);
        return user.getId();
    }
}
