package com.example.backend.common.login.presentation.dto.response;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;

public class UserInfo {

    private final Long memberId;
    private final String nickName;
    private final String profileImageUrl;
    private final NotificationMessageReadStatus notificationMessageReadStatus;

    public UserInfo(Member member, NotificationMessageReadStatus notificationMessageReadStatus) {
        this.memberId = member.getMemberId();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
        this.notificationMessageReadStatus = notificationMessageReadStatus;
    }

    public String getMemberId() {
        return String.valueOf(memberId);
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getNotificationMessageReadStatus() {
        return String.valueOf(notificationMessageReadStatus);
    }
}
