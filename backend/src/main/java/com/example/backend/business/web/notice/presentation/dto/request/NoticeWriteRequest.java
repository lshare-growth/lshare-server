package com.example.backend.business.web.notice.presentation.dto.request;

public class NoticeWriteRequest {

    private String thumbNailUrl;
    private String noticeTitle;
    private String noticeContent;

    private NoticeWriteRequest() {
    }

    public NoticeWriteRequest(String thumbNailUrl, String noticeTitle, String noticeContent) {
        this.thumbNailUrl = thumbNailUrl;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    @Override
    public String toString() {
        return String.format("작성 제목: %s, 작성 내용: %s", noticeTitle, noticeContent);
    }
}
