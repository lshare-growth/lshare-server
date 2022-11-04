package com.example.backend.common.login.application.github;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.infrastructure.member.command.MemberQueryDslCommandRepository;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.common.login.application.LoginService;
import com.example.backend.common.login.model.token.OauthClient;
import com.example.backend.common.login.model.token.jwt.JwtToken;
import com.example.backend.common.login.model.token.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GithubOauthService implements LoginService {

    private final MemberQueryDslCommandRepository memberQueryDslCommandRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Override
    public Member save(OauthClient oauthClient) {
        Member newMember = new Member(
                oauthClient.getClientId(),
                oauthClient.getProfileImage()
        );
        return memberQueryDslCommandRepository.save(newMember);
    }

    @Transactional
    public JwtToken createToken(Member member, NotificationMessageReadStatus messageReadStatus) {
        String accessToken = jwtTokenProvider.createAccessToken(member, messageReadStatus);
        String refreshToken = jwtTokenProvider.createRefreshToken(member, messageReadStatus);
        saveToken(String.valueOf(member.getMemberId()), refreshToken);
        return JwtToken.from(accessToken, refreshToken);
    }

    @Override
    public void saveToken(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
