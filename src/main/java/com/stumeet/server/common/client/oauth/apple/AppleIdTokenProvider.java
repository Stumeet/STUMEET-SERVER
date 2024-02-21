package com.stumeet.server.common.client.oauth.apple;

import com.stumeet.server.common.client.oauth.apple.model.ApplePublicKeyResponses;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Component
public class AppleIdTokenProvider {
    public PublicKey getSecretKey(ApplePublicKeyResponses publicKeys, String accessToken) {
        String kid = Jwts.parser()
                .build()
                .parseSignedClaims(accessToken)
                .getHeader()
                .getKeyId();

        ApplePublicKeyResponses.ApplePublicKeyResponse applePublicKey = publicKeys.keys().stream()
                .filter(key -> key.kid().equals(kid))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("매칭되는 kid가 존재하지 않습니다."));

        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(applePublicKey.n()));
        BigInteger publicExponent = new BigInteger(1, Base64.getUrlDecoder().decode(applePublicKey.e()));

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }


    public String extractUserId(PublicKey publicKey, String idToken) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(idToken)
                .getPayload()
                .getSubject();
    }
}
