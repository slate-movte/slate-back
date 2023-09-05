package com.movte.slate.oidc;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.oidc.domain.IdToken;
import com.movte.slate.oidc.dto.OidcPublicKeyDto;
import com.movte.slate.oidc.dto.OidcPublicKeysDto;
import com.movte.slate.util.TokenExtractor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import static com.movte.slate.global.exception.UnauthorizedExceptionCode.INVALID_TOKEN;
import static com.movte.slate.global.exception.UnauthorizedExceptionCode.TOKEN_EXPIRED;

public class IdTokenDecoder {
    private final String iss;
    private final String restApiKey;

    public IdTokenDecoder(String iss, String restApiKey) {
        this.iss = iss;
        this.restApiKey = restApiKey;
    }

    public IdToken decode(OidcPublicKeysDto oidcPublicKeysDto, String idToken) {
        String kid = getKid(idToken);
        OidcPublicKeyDto targetPublicKey = getTargetPublicKey(oidcPublicKeysDto, kid);
        Claims body = getOidcTokenJws(idToken, targetPublicKey.getN(), targetPublicKey.getE()).getBody();
        return new IdToken(
                body.getIssuer(),
                body.getAudience(),
                body.getSubject(),
                body.get("email", String.class),
                body.get("nickname", String.class),
                body.get("picture", String.class));
    }

    private OidcPublicKeyDto getTargetPublicKey(OidcPublicKeysDto oidcPublicKeysDto, String kid) {
        return oidcPublicKeysDto.getTargetOidcPublicKey(kid);
    }

    private String getKid(String idToken) {
        try {
            return (String) Jwts.parserBuilder()
                    .requireAudience(restApiKey)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(TokenExtractor.getUnsignedToken(idToken))
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
}
