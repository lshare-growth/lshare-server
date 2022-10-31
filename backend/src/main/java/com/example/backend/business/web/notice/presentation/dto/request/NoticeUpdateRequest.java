package com.example.backend.business.web.notice.presentation.dto.request;

public class NoticeUpdateRequest {

    private String noticeTitle;
    private String noticeContent;

    private NoticeUpdateRequest() {
    }

    public NoticeUpdateRequest(String noticeTitle, String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    @Override
    public String toString() {
        return String.format("수정할 공지사항 제목: %s, 수정할 내용: %s", noticeTitle, noticeContent);
    }
}
