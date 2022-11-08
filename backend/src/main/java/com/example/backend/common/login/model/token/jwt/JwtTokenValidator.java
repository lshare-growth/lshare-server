package com.example.backend.common.login.model.token.jwt;

import com.example.backend.common.login.model.token.TokenValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Objects;

@Component
public class JwtTokenValidator implements TokenValidator {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenValidator.class);

    private static final String MEMBER_ID = "memberId";

    @Value("${jwt.secret-key}")
    private String secretKey;

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private Key getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validate(String token) {
        try {
            token = parse(token);
            Claims claims = getClaims(token);
            return claims != null;
        } catch (UnsupportedJwtException e) {
            log.error("{} 접근이 허용되기 전 수신되었습니다.", token);
            return false;
        } catch (ExpiredJwtException e) {
            log.error("{} 기한이 만료된 토큰입니다.", token);
            return false;
        } catch (MalformedJwtException e) {
            log.error("{} 구조적 문제가 존재하는 토큰입니다.", token);
            return false;
        } catch (SignatureException e) {
            log.error("{} 서명이 위조된 토큰입니다.", token);
            return false;
        } catch (Exception e) {
            log.error("{} 처리되지 않은 예외입니다.", token);
            return false;
        }
    }

    public String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (accessToken == null) {
            return null;
        }
        if (!validate(accessToken)) {
            return null;
        }
        return parse(accessToken);
    }

    public String getRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("RefreshToken");
        if (refreshToken == null) {
            return null;
        }
        if (!validate(refreshToken)) {
            return null;
        }
        return parse(refreshToken);
    }

    public Long getMemberId(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return null;
        }
        return Long.valueOf(String.valueOf(claims.get(MEMBER_ID)));
    }

    public boolean validateRefreshToken(String refreshToken, String savedRefreshToken) {
        if (Objects.isNull(refreshToken) || Objects.isNull(savedRefreshToken)) {
            return false;
        }

        if (!refreshToken.equals(savedRefreshToken)) {
            return false;
        }

        Long refreshTokenPayLoad = getMemberId(refreshToken);
        Long savedRefreshTokenPayLoad = getMemberId(savedRefreshToken);

        if (Objects.isNull(refreshTokenPayLoad) || Objects.isNull(savedRefreshTokenPayLoad)) {
            return false;
        }

        return refreshTokenPayLoad.equals(savedRefreshTokenPayLoad);
    }

    public String getPayLoad(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            return String.valueOf(getClaims(token).get("nickName"));
        }
        return null;
    }

    public String getAttribute(String token, String attribute) {
        return String.valueOf(getClaims(token).get(attribute));
    }

    public String parse(String token) {
        return token.split(" ")[1];
    }
}
