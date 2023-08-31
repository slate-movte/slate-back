package com.movte.slate.domain.user.application.service;

import com.google.gson.JsonElement;
import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.oidc.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KakaoService {
    private final IdTokenDecoder idTokenDecoder;
    private final KakaoConfigProperties kakaoConfigProperties;
    private final NetworkCommunicator networkCommunicator;

    public IdTokenDto getIdToken(String authenticationCode) {
        String idToken = getKakaoIdToken(authenticationCode);
        String kid = getKid(idToken);
        OidcPublicKeys oidcPublicKeys = retrieveKakaoOpenKeysFromMyself();
        OidcPublicKeyDTO targetOidcPublicKey = oidcPublicKeys.getTargetOidcPublicKey(kid);
        return idTokenDecoder.decode(idToken, targetOidcPublicKey);
    }

    private String createQueryString(String authenticationCode, String restApiKey, String redirectUrl) {
        return "grant_type=authorization_code" +
                "&client_id=" + restApiKey +
                "&redirect_uri=" + redirectUrl +
                "&code=" + authenticationCode;
    }

    // 인가코드로 id token을 가져온다.
    private String getKakaoIdToken(String authenticationCode) {
        String tokenUrl = kakaoConfigProperties.getTokenUrl();
        String restApiKey = kakaoConfigProperties.getRestApiKey();
        String redirectUrl = kakaoConfigProperties.getRedirectUrl();
        String queryString = createQueryString(authenticationCode, restApiKey, redirectUrl);
        String resourceFromKakao = networkCommunicator.getResourceFromKakao(tokenUrl, queryString);
        JsonElement element = com.google.gson.JsonParser.parseString(resourceFromKakao);
        return element.getAsJsonObject().get("id_token").getAsString();
    }

    // 카카오 서버에서 OIDC public key를 가져온다.
    public OidcPublicKeys retrieveKakaoOpenKeysFromMyself() {
        String openKeyMyselfUrl = kakaoConfigProperties.getOpenKeyMyselfUrl();
        String openKey = networkCommunicator.getResponseUsingGet(openKeyMyselfUrl, "");
        String array = JsonParser.getObject(openKey, "keys");
        return new OidcPublicKeys(JsonParser.parse(array, OidcPublicKeyDTO.class));
    }

    public String retrieveKakaoOpenKeysFromKakao() {
        String openKeyKakaoUrl = kakaoConfigProperties.getOpenKeyKakaoUrl();
        return networkCommunicator.getResponseUsingGet(openKeyKakaoUrl, "");
    }

    private String getKid(String idToken) {
        String iss = kakaoConfigProperties.getIss(); // issuer
        String restApiKey = kakaoConfigProperties.getRestApiKey();
        try {
            return (String) Jwts.parserBuilder()
                    .requireAudience(restApiKey)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(TokenExtractor.getUnsignedToken(idToken))
                    .getHeader().get("kid");
            // ID 토큰에서 서명 분리해서 헤더, 페이로드만 파싱하기
        } catch (ExpiredJwtException e) { //파싱하면서 만료된 토큰인지 확인
            throw new UnauthorizedException(UnauthorizedExceptionCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
    }
}
