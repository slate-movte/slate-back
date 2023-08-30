package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.UserDto;
import com.movte.slate.domain.user.domain.OAuthProvider;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.UserRepository;
import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.oidc.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final KakaoService kakaoService;
    private final JwtOIDCProvider jwtOIDCProvider;
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final JwtConfigProperties jwtConfigProperties;
    private final JwtTokenIssuer jwtTokenIssuer;
    private final RandomKeyGenerator randomKeyGenerator;
    private final IdTokenDecoder idTokenDecoder;
    private RedisService redisService;

    // 인가 코드로 ID Token 가져와서 디코딩. 디코딩한 값 리턴.
    public DecodedIdTokenDto getDecodedIdToken(String authenticationCode) {
        String idToken = kakaoService.getKakaoIdToken(authenticationCode);
        String kid = kakaoService.getKid(idToken);
        OidcPublicKeys oidcPublicKeys = redisService.findKakaoOidcOpenKeys()
                .orElse(kakaoService.getKakaoOIDCOpenKeys());
        OidcPublicKeyDTO targetOidcPublicKey = oidcPublicKeys.getTargetOidcPublicKey(kid);
        return idTokenDecoder.decode(idToken, oidcPublicKeys);
    }

    // oauth provider(apple, kakao) 와 id token을 가지고서 DB에 이미 등록된 유저를 찾는다.
    // User가 존재하지 않으면 Optional.empty()를 리턴한다.
    public Optional<UserDto> findUser(OAuthProvider oAuthProvider, DecodedIdTokenDto decodedIdTokenDTO) {
        return userRepository.findByOauthIdAndOauthProvider(decodedIdTokenDTO.getOauthId(), oAuthProvider);
    }

    // 새로운 유저 정보를 만들어서 DB에 등록한다.
    public void signup(UserDto userDto) {
        // 영속성 엔티티로 변환해서 DB에 넘겨준다. DB에 저장한다. 
    }

    public Optional<User> findUser(OAuthProvider oauthProvider, String oauthId, String nickname) {
        // => oauth_id, o_auth_type 같이 조회!

        // 회원인지 여부 판단

        return findUser(oauthProvider, oauthId);

        // 회원 아니면 회원가입 처리
        if (signedUser == null) {
            User user = signup(oauthProvider, oauthId, nickname);

            // 프로필 이미지를 우리 서버에 저장해줘야 함.
//            UploadResponse dto = fileService.saveImageUrl(payload.getProfile_img_url(), userID);
//            user.updateImgUrl(dto.getImageURL(), dto.getThumnailURL());

            return UserDto.builder().user_id(user.getId()).build();
        }
    }

    private OidcPublicKeyDTO getTargetOidcPublicKey(String kid, List<OidcPublicKeyDTO> OIDCPublicKeys) {
        return OIDCPublicKeys.stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new ServerErrorException(ServerErrorExceptionCode.NO_OIDC_PUBLIC_KEY));
    }

    private User findUser(OAuthProvider oauthProvider, String oauthId) {
        return userRepository.findByOauthIdAndOauthProvider(oauthId, oauthProvider);
    }

    private User signup(OAuthProvider oauthProvider, String oauthId, String nickname) {
        User user = User.builder()
                .oauthId(oauthId)
                .oauthProvider(oauthProvider)
                .nickname(nickname)
                .build();
        return userRepository.save(user);
    }
}
