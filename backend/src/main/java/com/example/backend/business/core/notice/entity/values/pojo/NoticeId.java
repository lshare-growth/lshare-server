package com.example.backend.business.core.notice.entity.values.pojo;

public class NoticeId {

    private final Long noticeId;

    private NoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public static NoticeId from(Long noticeId) {
        return new NoticeId(noticeId);
    }

    public Long getNoticeId() {
        return noticeId;
    }

    @Override
    public String toString() {
        return String.format("공지사항 아이디: %s", noticeId);
    }
}
