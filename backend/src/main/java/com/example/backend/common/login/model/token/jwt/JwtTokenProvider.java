package com.example.backend.common.login.model.token.jwt;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.common.login.model.token.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private static final String MEMBER_ID = "memberId";
    private static final String NICK_NAME = "nickName";
    private static final String PROFILE_IMAGE_URL = "profileImageUrl";
    private static final String NOTIFICATION_CHECK = "notificationCheck";

    private static final long FIFTEEN_MINUTES = 1000 * 60 * 15;
    private static final long TWO_WEEKS = 1000 * 60 * 12 * 24 * 7;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public JwtTokenProvider() {
    }

    private Key getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Member member, NotificationMessageReadStatus messageReadStatus) {
        Claims claims = Jwts.claims();
        claims.put(MEMBER_ID, member.getMemberId());
        claims.put(NICK_NAME, member.getNickName());
        claims.put(PROFILE_IMAGE_URL, member.getProfileImageUrl());
        claims.put(NOTIFICATION_CHECK, messageReadStatus);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + FIFTEEN_MINUTES))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(Member member, NotificationMessageReadStatus messageReadStatus) {
        Claims claims = Jwts.claims();
        claims.put(MEMBER_ID, member.getMemberId());
        claims.put(NICK_NAME, member.getNickName());
        claims.put(PROFILE_IMAGE_URL, member.getProfileImageUrl());
        claims.put(NOTIFICATION_CHECK, messageReadStatus);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TWO_WEEKS))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String renewAccessToken(Long memberId) {
        Claims claims = Jwts.claims();
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + FIFTEEN_MINUTES))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
