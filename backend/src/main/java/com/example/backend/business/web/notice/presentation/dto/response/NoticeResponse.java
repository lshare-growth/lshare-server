package com.example.backend.business.web.notice.presentation.dto.response;

import com.example.backend.business.core.notice.entity.Notice;

import java.time.LocalDateTime;

public class NoticeResponse {

    private final Long noticeId;
    private final String nickName;
    private final String profileImageUrl;
    private final String noticeTitle;
    private final String noticeContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    private NoticeResponse(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.nickName = notice.getAdminNickName();
        this.profileImageUrl = notice.getAdminProfileImageUrl();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.createdAt = notice.getCreatedAt();
        this.lastModifiedAt = notice.getLastModifiedAt();
    }

    public static NoticeResponse of(Notice notice) {
        return new NoticeResponse(notice);
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public String toString() {
        return String.format("공지글 작성자: %s, 공지글 제목: %s, 공지글 내용: %s, 작성일: %s, 마지막 수정일: %s", nickName, noticeTitle, noticeContent, createdAt, lastModifiedAt);
    }
}
