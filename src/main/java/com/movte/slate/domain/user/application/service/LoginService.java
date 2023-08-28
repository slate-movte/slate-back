package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.domain.User;
import javax.transaction.Transactional;

import com.movte.slate.domain.user.domain.OAuthType;
import com.movte.slate.domain.user.dto.OIDCPublicKeyDTO;
import com.movte.slate.domain.user.dto.TokenResponseDTO;
import com.movte.slate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final KakaoService kakaoService;
    private final JwtOIDCProvider jwtOIDCProvider;

    private final UserRepository userRepository;

    private final RedisTemplate<String, String> redisTemplate;

    private final Long refreshToeknValidTime = Duration.ofDays(14).toMillis();

    private final FileService fileService;

    // 내 어플리케이션 key
    @Value("${kakao.rest-api-key}")
    private String REST_API_KEY;

    @Value("${kakao.iss}")
    private String ISS;

    /**
     * 카카오 로그인 전체 과정 (인가코드 받은 이후 ~ access, refresh 토큰 반환)
     *
     * @param code
     * @return
     */
    public TokenResponseDTO KakaoLogin(String code) {
        // 코드 성공적으로 받아옴
        log.info("code: "+code);

        // 인가 코드 이용해서 idToken 받아오기
        String idToken = kakaoService.getKakaoIdToken(code, REST_API_KEY);
        log.info("idToken : " + idToken);

        // idToken 페이로드(iss, aud, exp) 검증 후 kid 값 가져오기
        String kid = jwtOIDCProvider.getKidFromUnsignedTokenHeader(idToken, ISS, REST_API_KEY);
        log.info("kid : " + kid);

        // 공개키 목록 조회 (JSON으로 받은 것 객체로 파싱해서 가져옴)
        List<OIDCPublicKeyDTO> OIDCPublicKeys = kakaoService.getKakaoOIDCOpenKeys();

        // kid와 동일한 키값을 가진 공개키로 ID 토큰 유효성 검증
        // kid와 동일한 키값 가진 공개키 가져오기
        OIDCPublicKeyDTO oidcPublicKeyDto = OIDCPublicKeys.stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst()
                .orElseThrow();

        // 검증이 된 토큰에서 바디를 꺼내온다.
        OIDCDecodePayloadDTO payload = jwtOIDCProvider.getOIDCTokenBody(idToken, oidcPublicKeyDto.getN(), oidcPublicKeyDto.getE());
        log.info("OIDCDecodePayload : " + payload.toString());

        // 회원가입 된 회원인지 찾기 -> 회원 정보(sub) 없다면 회원가입 처리
        // user 테이블 - oauth_id, o_auth_provider, (email), nickname, profile_img_url
        UserDTO UserDTO = signUp(payload, OAuthType.KAKAO);
        log.info(UserDTO.toString());

        // 로그인 처리를 위해 jwt 토큰 생성 (access token, refresh token)
        String secretKey = JwtProvider.getSecretKey();
        log.info("secretKey : " + secretKey);
        String refreshToken = JwtProvider.createRefreshToken(UserDTO, secretKey);
        String accessToken = JwtProvider.createAccessToken(UserDTO, secretKey);
        log.info("refreshToken : " + refreshToken);
        log.info("accessToken : " + accessToken);

        // refresh token은 redis에 저장
        TokenResponseDTO tokenResponseDto = new TokenResponseDTO(accessToken, refreshToken);

        String redisSub = UserDTO.getUser_id().toString();
        // Redis에 저장 - 만료 시간 설정을 통해 자동 삭제 처리
        redisTemplate.opsForValue().set(
                redisSub,
                refreshToken,
                refreshTokenValidTime,
                TimeUnit.MILLISECONDS
        );

        // client에게 access token, refresh token 반환
        return tokenResponseDto;
    }


    public UserDto signUp(OIDCDecodePayloadDTO payload, OAuthType oauthType) {

        // sub로 판단하려면.. 구글이랑 겹치지는 않나??
        // => oauth_id, o_auth_type 같이 조회!

        // 회원인지 여부 판단
        User signedUser = userRepository.findByOauthIdAndOauthTypeandIsDeleted(payload.getSub(), oauthType, false);
        Long id = 0L;

        // 회원 아니면 회원가입 처리
        if(signedUser == null) {
            log.info("SignUp NO");
            User user = User.builder()
                    .oauthId(payload.getSub())
                    .oauthType(oauthType)
                    .email(payload.getEmail())
                    .nickname(payload.getNickname())
                    .build();
            log.info(user.toString());
            Long userID = userRepository.save(user).getId();

            // 프로필 이미지를 우리 서버에 저장해줘야 함.
            UploadResponse dto = fileService.saveImageUrl(payload.getProfile_img_url(), userID);
            user.updateImgUrl(dto.getImageURL(), dto.getThumnailURL());

            return UserDTO.builder().user_id(user.getId()).build();
        }

        // 로그인 여부 true로 전환
        log.info("SignUp YES");
        signedUser.updateIsLogined(true);
        userRepository.save(signedUser);
        log.info(signedUser.toString());


    }


}
