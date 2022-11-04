package com.example.backend.common.login.facade;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.CurrentLoginIpAddress;
import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.notification.entity.Notification;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.notification.application.NotificationCommandService;
import com.example.backend.business.web.notification.application.NotificationQueryService;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.login.application.github.GithubOauthService;
import com.example.backend.common.login.model.token.OauthClient;
import com.example.backend.common.login.model.token.jwt.JwtToken;
import com.example.backend.common.login.presentation.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class GithubOauthFacade {

    private static final Logger log = LoggerFactory.getLogger(GithubOauthFacade.class);

    private final MemberQueryService memberQueryService;
    private final GithubOauthService githubOauthService;
    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;

    @Transactional
    public LoginResponse save(OauthClient oauthClient, CurrentLoginIpAddress ipAddress) {
        Optional<Member> findMember = memberQueryService.findByGithubId(GithubId.from(oauthClient.getClientId()));

        if (findMember.isPresent()) {
            Member existMember = findMember.get();
            existMember.updateIpAddress(ipAddress);
            log.info("{}번 회원님이 {}에서 로그인하셨습니다.", existMember.getMemberId(), ipAddress);

            NotificationMessageReadStatus messageReadStatus = notificationQueryService.findNotificationByMemberId(MemberId.from(existMember.getMemberId()));
            JwtToken jwtToken = githubOauthService.createToken(existMember, messageReadStatus);
            return LoginResponse.of(existMember, jwtToken, messageReadStatus);
        }

        Member source = memberQueryService.findByGithubId(GithubId.from("LShare-Official")).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Member target = githubOauthService.save(oauthClient);
        target.updateIpAddress(ipAddress);
        Notification welcomeNotification = notificationCommandService.save(Notification.createWelcomeMessage(source, target));

        JwtToken jwtToken = githubOauthService.createToken(target, welcomeNotification.getNotificationCheck());

        log.info("{}번 회원님이 {}에서 로그인 하셨습니다.", target.getMemberId(), ipAddress);
        return LoginResponse.of(target, jwtToken, welcomeNotification.getNotificationCheck());
    }
}
