package com.example.backend.business.web.notice.application;

import com.example.backend.business.core.admin.entity.Admin;
import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.NoticeContent;
import com.example.backend.business.core.notice.entity.values.NoticeTitle;
import com.example.backend.business.core.notice.infrastrucrue.NoticeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeCommandService {

    private final NoticeJpaRepository noticeJpaRepository;

    @Transactional
    public Notice writeNotice(Notice notice) {
        return noticeJpaRepository.save(notice);
    }

    @Transactional
    public void deleteNotice(Admin admin, Notice notice) {
        notice.delete(admin);
    }

    @Transactional
    public void updateNotice(Admin admin, Notice notice, NoticeTitle title, NoticeContent content) {
        notice.updateNotice(admin, title, content);
    }
}
