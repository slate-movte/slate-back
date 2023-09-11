package com.movte.slate.oidc.application.service;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.kakao.application.usecase.GetKakaoPublicKeyFromMyselfUseCase;
import com.movte.slate.oidc.application.response.GetOidcPublicKeysResponse;
import com.movte.slate.oidc.application.response.OidcPublicKeyDto;
import com.movte.slate.oidc.application.usecase.DecodeIdTokenUseCase;
import com.movte.slate.oidc.domain.IdToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import static com.movte.slate.global.exception.UnauthorizedExceptionCode.INVALID_TOKEN;
import static com.movte.slate.global.exception.UnauthorizedExceptionCode.TOKEN_EXPIRED;

@Service
@RequiredArgsConstructor
public class DecodeIdTokenService implements DecodeIdTokenUseCase {
    private final GetKakaoPublicKeyFromMyselfUseCase getKakaoPublicKeyFromMyselfUseCase;
    @Value("${kakao.iss}")
    private String iss;
    @Value("${kakao.restApiKey}")
    private String restApiKey;

    @Override
    public IdToken decodeIdToken(String idToken) {
        String kid = getKid(idToken);
        GetOidcPublicKeysResponse response = getKakaoPublicKeyFromMyselfUseCase.get();
        OidcPublicKeyDto targetOidcPublicKey = response.getTargetOidcPublicKey(kid);
        Claims body = getOidcTokenJws(idToken, targetOidcPublicKey.getN(), targetOidcPublicKey.getE()).getBody();
        return new IdToken(
                body.getIssuer(),
                body.getAudience(),
                body.getSubject(),
                body.get("email", String.class),
                body.get("nickname", String.class),
                body.get("picture", String.class));
    }

    private String getKid(String idToken) {
        try {
            return (String) Jwts.parserBuilder()
                    .requireAudience(restApiKey)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(getUnsignedToken(idToken))
                    .getHeader().get("kid");
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
    }


    private Jws<Claims> getOidcTokenJws(String token, String modulus, String exponent) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getRsaPublicKey(modulus, exponent))
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new UnauthorizedException(INVALID_TOKEN);
        }
    }

    private Key getRsaPublicKey(String modulus, String exponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }

    private String getUnsignedToken(String token) throws Exception {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) throw new Exception();
        return splitToken[0] + "." + splitToken[1] + "."; // 헤더, 페이로드 반환
    }
}
