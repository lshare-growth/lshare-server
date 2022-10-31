package com.example.backend.business.web.notice.application;

import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.pojo.NoticeId;
import com.example.backend.business.core.notice.infrastrucrue.query.NoticeQueryDslQueryRepository;
import com.example.backend.business.web.notice.presentation.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeQueryService {

    private final NoticeQueryDslQueryRepository noticeQueryDslQueryRepository;

    @Transactional(readOnly = true)
    public Optional<Notice> findNoticeById(NoticeId noticeId){
        return noticeQueryDslQueryRepository.findNoticeById(noticeId);
    }

    @Transactional(readOnly = true)
    public Page<NoticeResponse> findAll() {
        return noticeQueryDslQueryRepository.findAll();
    }
}
