package com.movte.slate.oidc;

import com.movte.slate.global.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import static com.movte.slate.global.exception.UnauthorizedExceptionCode.INVALID_TOKEN;
import static com.movte.slate.global.exception.UnauthorizedExceptionCode.TOKEN_EXPIRED;

@Component
public class IdTokenDecoder {
    public IdTokenDto decode(String idToken, OidcPublicKeyDTO oidcPublicKey) {
        Claims body = getOidcTokenJws(idToken, oidcPublicKey.getN(), oidcPublicKey.getE()).getBody();
        return new IdTokenDto(
                body.getIssuer(),
                body.getAudience(),
                body.getSubject(),
                body.get("email", String.class),
                body.get("nickname", String.class),
                body.get("picture", String.class));
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
