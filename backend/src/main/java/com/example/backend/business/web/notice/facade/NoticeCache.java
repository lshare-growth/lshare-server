package com.example.backend.business.web.notice.facade;

import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.notice.presentation.dto.response.NoticeResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class NoticeCache {

    private CustomPage<NoticeResponse> caches = CustomPage.from(new PageImpl<>(Collections.emptyList()));

    public CustomPage<NoticeResponse> getCache() {
        return caches;
    }

    public boolean isEmpty() {
        return caches.isEmpty();
    }

    public void put(CustomPage<NoticeResponse> caches) {
        this.caches = caches;
    }
}
