package com.example.backend.business.web.notice.presentation;

import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.pojo.NoticeId;
import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.notice.facade.NoticeQueryFacade;
import com.example.backend.business.web.notice.presentation.dto.response.NoticePageResponse;
import com.example.backend.business.web.notice.presentation.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeQueryController {

    private final NoticeQueryFacade noticeQueryFacade;

    @GetMapping
    public ResponseEntity<NoticePageResponse> findNoticePage(Pageable pageable) {

        CustomPage<NoticeResponse> noticePage = noticeQueryFacade.findNoticePage();

        return ResponseEntity.ok(NoticePageResponse.of(noticePage));
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> findNoticeById(@PathVariable Long noticeId) {

        Notice findNotice = noticeQueryFacade.findNoticeById(
                NoticeId.from(noticeId)
        );

        return ResponseEntity.ok(NoticeResponse.of(findNotice));
    }
}
