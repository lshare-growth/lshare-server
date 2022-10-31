package com.example.backend.business.web.member.presentation.member.dto.request;

import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;

public class NotificationUpdateRequest {

    private Long memberId;
    private Long notificationId;
    private String notificationReadStatus;

    private NotificationUpdateRequest() {
    }

    public NotificationUpdateRequest(Long memberId, Long notificationId, String notificationReadStatus) {
        this.memberId = memberId;
        this.notificationId = notificationId;
        this.notificationReadStatus = notificationReadStatus;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public NotificationMessageReadStatus getNotificationReadStatus() {
        return NotificationMessageReadStatus.findNotificationByType(notificationReadStatus);
    }

    @Override
    public String toString() {
        return String.format("변경을 신청한 회원 아이디: %s, 상태를 변경할 알림 아이디: %s, 변경할 알림읽음 상태: %s", memberId, notificationId, notificationReadStatus);
    }
}
