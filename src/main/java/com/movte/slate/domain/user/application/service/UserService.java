package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.UserDto;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.domain.UserState;
import com.movte.slate.domain.user.repository.UserRepository;
import com.movte.slate.global.exception.*;
import com.movte.slate.oidc.IdTokenDto;
import com.movte.slate.oidc.JwtToken;
import com.movte.slate.oidc.JwtTokenFactory;
import com.movte.slate.oidc.JwtTokenIssuer;
import io.jsonwebtoken.ExpiredJwtException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenFactory jwtTokenFactory;
    private final JwtTokenIssuer jwtTokenIssuer;

    // accessToken 정보를 통해 유저 정보 조회
    public UserDto userInfo(String accessToken) {
        JwtToken accessJwt = jwtTokenFactory.create(accessToken);
        Long userId = accessJwt.getUserId();
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.CANNOT_FIND_USER));
        return UserDto.of(user);
    }

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

    public void saveRefreshToken(long userId, String refreshToken) {
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.CANNOT_FIND_USER));
        user.setRefreshToken(refreshToken);
    }

//    public String refreshAccessToken(String accessToken, String refreshToken) {
//        JwtToken accessJwt = jwtTokenFactory.create(accessToken);
//        JwtToken refreshJwt = jwtTokenFactory.create(refreshToken);
//        Long userId = accessJwt.getUserId();
//        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException(BadRequestExceptionCode.NO_REFRESH_TOKEN));
//        String refreshTokenInDb = user.getRefreshToken();
//        if(refreshTokenInDb==null) throw new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN);
//        if(!refreshTokenInDb.equals(refreshToken))
//            throw new BadRequestException(BadRequestExceptionCode.REFRESH_TOKEN_NOT_EQUAL);
//        if(refreshJwt.isExpired(new Date()))
//            throw new UnauthorizedException(UnauthorizedExceptionCode.TOKEN_EXPIRED);
//
//        // access token 재발급
//        return jwtTokenIssuer.createAccessToken(UserDto.of(user));
//    }
    public String refreshAccessToken(String accessToken, String refreshToken) {
        try {
            jwtTokenFactory.create(accessToken);
        } catch (ExpiredJwtException expiredAccessTokenException){
            // access token이 만료 된경우
            try {
                JwtToken refreshJwt = jwtTokenFactory.create(refreshToken);
                Long userId = refreshJwt.getUserId();
                User user = userRepository.findById(userId).orElseThrow(
                        () -> new BadRequestException(BadRequestExceptionCode.NO_REFRESH_TOKEN));
                String refreshTokenInDb = user.getRefreshToken();
                if (refreshTokenInDb == null)
                    throw new UnauthorizedException(UnauthorizedExceptionCode.INVALID_TOKEN);
                if (!refreshTokenInDb.equals(refreshToken))
                    throw new BadRequestException(BadRequestExceptionCode.REFRESH_TOKEN_NOT_EQUAL);
                // access token 재발급
                return jwtTokenIssuer.createAccessToken(UserDto.of(user));
            } catch (ExpiredJwtException expiredRefreshTokenException){
                // refresh token이 만료 된경우
                throw new UnauthorizedException(UnauthorizedExceptionCode.TOKEN_EXPIRED);
            }
        }
        return accessToken;
    }

    public UserDto setPendingUserInformation(String accessToken, String nickname, String profile_image_url) {
        JwtToken accessJwt = jwtTokenFactory.create(accessToken);
        Long userId = accessJwt.getUserId();
        // 영속성 엔티티로 변환해서 DB에 넘겨준다. DB에 저장한다.
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.CANNOT_FIND_USER));
        user.setNickname(nickname);
        user.setProfileImageUrl(profile_image_url);
        user.setUserSate(UserState.APPROVED);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user); // user 저장
        return UserDto.of(user);
    }

    public UserDto signupUser(String accessToken, String nickname, String profile_image_url) {
        JwtToken accessJwt = jwtTokenFactory.create(accessToken);
        Long userId = accessJwt.getUserId();
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.CANNOT_FIND_USER));
        user.setNickname(nickname);
        user.setProfileImageUrl(profile_image_url);
        user.setUserSate(UserState.APPROVED);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return UserDto.of(user);
    }

    public UserDto userInfoEdit(String accessToken, String nickname, String profileImageUrl) {
        JwtToken accessJwt = jwtTokenFactory.create(accessToken);
        Long userId = accessJwt.getUserId();
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.CANNOT_FIND_USER));
        user.setNickname(nickname);
        user.setProfileImageUrl(profileImageUrl);
        user = userRepository.save(user);
        return UserDto.of(user);
    }
}
