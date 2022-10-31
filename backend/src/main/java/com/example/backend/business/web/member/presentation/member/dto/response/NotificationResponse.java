package com.example.backend.business.web.member.presentation.member.dto.response;

import com.example.backend.business.core.notification.entity.Notification;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;

import java.time.LocalDateTime;

public class NotificationResponse {

    private final Long notificationId;
    private final String message;
    private final String sourceProfileImageUrl;
    private final NotificationMessageReadStatus messageReadStatus;
    private final LocalDateTime createdAt;

    public NotificationResponse(Notification notification) {
        this.notificationId = notification.getNotificationId();
        this.message = notification.getMessage();
        this.sourceProfileImageUrl = notification.getSourceProfileImageUrl();
        this.messageReadStatus = notification.getNotificationCheck();
        this.createdAt = notification.getCreatedAt();
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public String getSourceProfileImageUrl() {
        return sourceProfileImageUrl;
    }

    public NotificationMessageReadStatus getNotificationCheck() {
        return messageReadStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("알림 아이디: %s, 알림 메시지: %s, 알림 체크: %s", notificationId, message, messageReadStatus);
    }
}
