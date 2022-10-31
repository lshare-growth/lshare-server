package com.example.backend.business.web.notice.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomPage;

public class NoticePageResponse {

    private final CustomPage<NoticeResponse> page;

    private NoticePageResponse(CustomPage<NoticeResponse> page) {
        this.page = page;
    }

    public static NoticePageResponse of(CustomPage<NoticeResponse> page) {
        return new NoticePageResponse(page);
    }

    public CustomPage<NoticeResponse> getPage() {
        return page;
    }
}
