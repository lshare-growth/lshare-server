package com.example.backend.business.web.notice.presentation.dto.response;

import com.example.backend.business.core.notice.entity.Notice;

public class NoticeWriteResponse {

    private final Long noticeId;

    public NoticeWriteResponse(Notice notice) {
        this.noticeId = notice.getNoticeId();
    }

    public static NoticeWriteResponse of(Notice notice) {
        return new NoticeWriteResponse(notice);
    }

    public Long getNoticeId() {
        return noticeId;
    }

    @Override
    public String toString() {
        return String.format("생성된 공지사항 아이디: %s", noticeId);
    }
}
