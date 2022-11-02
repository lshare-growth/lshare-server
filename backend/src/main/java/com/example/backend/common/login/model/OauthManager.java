package com.example.backend.common.login.model;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.infrastructure.member.query.MemberQueryDslQueryRepository;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.business.core.notification.infrastructure.query.NotificationQueryDslQueryRepository;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.login.presentation.dto.response.UserInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
public class OauthManager {

    private final NotificationQueryDslQueryRepository notificationQueryDslQueryRepository;
    private final MemberQueryDslQueryRepository memberQueryDslQueryRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public OauthManager(NotificationQueryDslQueryRepository notificationQueryDslQueryRepository, MemberQueryDslQueryRepository memberQueryDslQueryRepository, RedisTemplate<String, Object> redisTemplate) {
        this.notificationQueryDslQueryRepository = notificationQueryDslQueryRepository;
        this.memberQueryDslQueryRepository = memberQueryDslQueryRepository;
        this.redisTemplate = redisTemplate;
    }

    @Transactional(readOnly = true)
    public String getRefreshToken(String refreshToken) {
        return (String) redisTemplate.opsForValue().get(refreshToken);
    }

    @Transactional(readOnly = true)
    public Boolean validatePageAuthorization(Long memberId, Long studyId) {
        return memberQueryDslQueryRepository.validatePageAuthorizationById(memberId, studyId);
    }

    @Transactional(readOnly = true)
    public UserInfo getUserInfo(MemberId memberId) {
        NotificationMessageReadStatus notificationMessageReadStatus = notificationQueryDslQueryRepository.findNotificationReadByMemberId(memberId);
        return new UserInfo(memberQueryDslQueryRepository.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION)), notificationMessageReadStatus);
    }
}
