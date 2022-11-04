package com.example.backend.common.login.model;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.CurrentLoginIpAddress;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.infrastructure.member.query.MemberQueryDslQueryRepository;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.business.core.notification.infrastructure.query.NotificationQueryDslQueryRepository;
import com.example.backend.business.web.member.application.member.MemberCommandService;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.login.presentation.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class OauthManager {

    private final NotificationQueryDslQueryRepository notificationQueryDslQueryRepository;
    private final MemberQueryDslQueryRepository memberQueryDslQueryRepository;
    private final MemberCommandService memberCommandService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional(readOnly = true)
    public String getRefreshToken(String refreshToken) {
        return (String) redisTemplate.opsForValue().get(refreshToken);
    }

    @Transactional(readOnly = true)
    public Boolean validatePageAuthorization(Long memberId, Long studyId) {
        return memberQueryDslQueryRepository.validatePageAuthorizationById(memberId, studyId);
    }

    @Transactional
    public UserInfo getUserInfo(MemberId memberId, CurrentLoginIpAddress ipAddress) {
        log.info("getUserIfno");
        Member findMember = memberQueryDslQueryRepository.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        NotificationMessageReadStatus notificationMessageReadStatus = notificationQueryDslQueryRepository.findById(memberId);

        memberCommandService.updateIpAddress(findMember, ipAddress);
        log.info("{}님이 {}에서 회원정보를 갱신했습니다.", findMember, ipAddress);
        return new UserInfo(findMember, notificationMessageReadStatus);
    }
}
