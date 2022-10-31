package com.example.backend.business.web.notice.facade;

import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.pojo.NoticeId;
import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.notice.application.NoticeQueryService;
import com.example.backend.business.web.notice.presentation.dto.response.NoticeResponse;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.notice.NoticeTypeException.NOTICE_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class NoticeQueryFacade {

    private final NoticeQueryService noticeQueryService;
    private final NoticeCache noticeCache;

    @Transactional(readOnly = true)
    public CustomPage<NoticeResponse> findNoticePage() {
        if (noticeCache.isEmpty()) {
            CustomPage<NoticeResponse> page = CustomPage.from(noticeQueryService.findAll());
            noticeCache.put(page);
            return page;
        }
        return noticeCache.getCache();
    }

    @Transactional(readOnly = true)
    public Notice findNoticeById(NoticeId noticeId) {
        return noticeQueryService.findNoticeById(noticeId).orElseThrow(()->new BusinessException(NOTICE_NOT_FOUND_EXCEPTION));
    }
}
