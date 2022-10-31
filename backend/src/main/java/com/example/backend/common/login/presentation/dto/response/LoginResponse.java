package com.example.backend.common.login.presentation.dto.response;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.common.login.model.token.jwt.JwtToken;

public class LoginResponse {

    private final Member member;
    private final JwtToken jwtToken;
    private final NotificationMessageReadStatus notificationMessageReadStatus;

    private LoginResponse(Member member, JwtToken jwtToken, NotificationMessageReadStatus notificationMessageReadStatus) {
        this.member = member;
        this.jwtToken = jwtToken;
        this.notificationMessageReadStatus = notificationMessageReadStatus;
    }

    public static LoginResponse of(Member member, JwtToken jwtToken, NotificationMessageReadStatus notificationMessageReadStatus) {
        return new LoginResponse(member, jwtToken, notificationMessageReadStatus);
    }

    public Member getMember() {
        return member;
    }

    public JwtToken getJwtToken() {
        return jwtToken;
    }

    public String getAccessToken() {
        return jwtToken.getAccessToken();
    }

    public String getRefreshToken() {
        return jwtToken.getRefreshToken();
    }

    public NotificationMessageReadStatus getNotificationRead() {
        return notificationMessageReadStatus;
    }

    @Override
    public String toString() {
        return String.format("회원 가입한 회원: %s, JwtToken: %s", member.getGithubId(), jwtToken);
    }
}
