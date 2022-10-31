package com.example.backend.business.web.notice.facade;

import com.example.backend.business.core.admin.entity.Admin;
import com.example.backend.business.core.admin.entity.value.pojo.AdminId;
import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.NoticeContent;
import com.example.backend.business.core.notice.entity.values.NoticeTitle;
import com.example.backend.business.core.notice.entity.values.pojo.NoticeId;
import com.example.backend.business.web.admin.application.AdminCommandService;
import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.notice.application.NoticeCommandService;
import com.example.backend.business.web.notice.application.NoticeQueryService;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class NoticeCommandFacade {

    private final NoticeQueryService noticeQueryService;
    private final NoticeCommandService noticeCommandService;
    private final AdminCommandService adminCommandService;
    private final NoticeCache noticeCache;

    @Transactional
    public Notice writeNotice(AdminId adminId,
                              NoticeTitle title,
                              NoticeContent content) {

        Admin findAdmin = adminCommandService.findById(adminId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Notice newNotice = noticeCommandService.writeNotice(new Notice(findAdmin, title, content));
        noticeCache.put(CustomPage.from(noticeQueryService.findAll()));
        return newNotice;
    }

    @Transactional
    public void updateNotice(AdminId adminId, NoticeId noticeId, NoticeTitle noticeTitle, NoticeContent noticeContent) {
        Admin findAdmin = adminCommandService.findById(adminId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Notice findNotice = noticeQueryService.findNoticeById(noticeId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        noticeCommandService.updateNotice(findAdmin, findNotice, noticeTitle, noticeContent);
        noticeCache.put(CustomPage.from(noticeQueryService.findAll()));
    }

    @Transactional
    public void deleteNotice(AdminId adminId, NoticeId noticeId) {
        Admin findAdmin = adminCommandService.findById(adminId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Notice findNotice = noticeQueryService.findNoticeById(noticeId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        noticeCommandService.deleteNotice(findAdmin, findNotice);
        noticeCache.put(CustomPage.from(noticeQueryService.findAll()));
    }
}
