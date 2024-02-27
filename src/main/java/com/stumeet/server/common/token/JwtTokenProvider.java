package com.stumeet.server.common.token;

import com.stumeet.server.common.auth.model.LoginMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";

    private final String issuer;
    private final String secret;

    private final long tokenValidityTime;
    private final SecretKey secretKey;

    public JwtTokenProvider(
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.validate-time}") long tokenValidityTime
    ) {
        this.issuer = issuer;
        this.secret = secret;
        this.tokenValidityTime = tokenValidityTime;
        this.secretKey = createSecretKey(secret);
    }

    private SecretKey createSecretKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(LoginMember member) {
        String authorities = member.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = new Date().getTime();
        Date validityTime = new Date(now + tokenValidityTime);

        return Jwts.builder()
                .issuer(issuer)
                .subject(String.valueOf(member.getMember().getId()))
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(secretKey)
                .expiration(validityTime)
                .compact();
    }

    public String generateRefreshToken(Long memberId) {
        return Jwts.builder()
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(String.valueOf(memberId))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.warn("신뢰할 수 없는 JWT 토큰 입니다.", e);
        }
        return false;
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSubject(String token) {
        return getClaims(token)
                .getSubject();
    }

}
