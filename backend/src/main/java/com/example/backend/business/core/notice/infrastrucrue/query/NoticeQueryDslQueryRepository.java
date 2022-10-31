package com.example.backend.business.core.notice.infrastrucrue.query;

import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.pojo.NoticeId;
import com.example.backend.business.web.notice.presentation.dto.response.NoticeResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.backend.business.core.admin.entity.QAdmin.admin;
import static com.example.backend.business.core.notice.entity.QNotice.notice;

@Repository
public class NoticeQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Notice> findNoticeById(NoticeId noticeId) {
        return Optional.ofNullable(queryFactory.selectFrom(notice)
                .join(notice.admin, admin)
                .fetchJoin()
                .where(notice.noticeId.eq(noticeId.getNoticeId()))
                .fetchOne());
    }

    public Page<NoticeResponse> findAll() {
        List<NoticeResponse> content = queryFactory.selectFrom(notice)
                .join(notice.admin)
                .fetchJoin()
                .stream()
                .map(NoticeResponse::of)
                .collect(Collectors.toUnmodifiableList());

        if (content.isEmpty()) {
            return new PageImpl<>(content);
        }

        return new PageImpl<>(content);
    }
}
