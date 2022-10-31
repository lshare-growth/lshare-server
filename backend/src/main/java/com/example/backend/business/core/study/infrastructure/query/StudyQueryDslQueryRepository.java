package com.example.backend.business.core.study.infrastructure.query;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.StudyJoinRequest;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyHashTag;
import com.example.backend.business.core.study.entity.StudyMember;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.HashTagIds;
import com.example.backend.business.web.study.presentation.dto.request.StudyJoinRequestId;
import com.example.backend.common.mapper.database.SortOrder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.backend.business.core.common.Deleted.FALSE;
import static com.example.backend.business.core.like.entity.QLike.like;
import static com.example.backend.business.core.member.entity.QMember.member;
import static com.example.backend.business.core.member.entity.values.QStudyJoinRequest.studyJoinRequest;
import static com.example.backend.business.core.study.entity.QStudy.study;
import static com.example.backend.business.core.study.entity.QStudyHashTag.studyHashTag;
import static com.example.backend.business.core.study.entity.QStudyMember.studyMember;
import static com.example.backend.business.core.study.entity.StudyMemberRole.LEADER;

@Repository
public class StudyQueryDslQueryRepository {

    private static final int FIRST_PAGE = 0;
    private static final int FIXED_PAGE_SIZE = 10;

    private final JPAQueryFactory queryFactory;

    public StudyQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Study> findStudyById(StudyId studyId) {
        return Optional.ofNullable(queryFactory.selectFrom(study)
                .where(study.studyId.eq(studyId.getStudyId()).and(study.deleted.eq(FALSE)))
                .fetchOne());
    }

    public Slice<StudyMember> findStudyLandingPage(Pageable pageable) {
        List<StudyMember> content = queryFactory.selectFrom(studyMember)
                .join(studyMember.study, study)
                .fetchJoin()
                .join(studyMember.member, member)
                .fetchJoin()
                .where(study.deleted.eq(FALSE))
                .offset(pageable.getOffset())
                .limit(FIXED_PAGE_SIZE + 1)
                .orderBy(study.studyId.desc())
                .fetch();

        if (content.isEmpty()) {
            return new SliceImpl<>(Collections.emptyList(), pageable, false);
        }

        boolean hasNext = false;

        if (content.size() > 10) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);
    }

    public Page<Study> findStudyPage(Pageable pageable, SortOrder sortOrder) {
        List<Study> content = queryFactory.selectFrom(study)
                .where(study.deleted.eq(FALSE))
                .offset(pageable.getOffset())
                .orderBy(sortOrder.getOrder())
                .limit(FIXED_PAGE_SIZE)
                .fetch();

        if (content.isEmpty()) {
            return PageableExecutionUtils.getPage(Collections.emptyList(), pageable, () -> 0);
        }

        JPAQuery<Long> totalCount = queryFactory.select(Wildcard.count)
                .where(study.deleted.eq(FALSE))
                .from(study);

        return PageableExecutionUtils.getPage(content, pageable, () -> totalCount.fetch().get(0));
    }

    public Page<Study> findStudiesByTitle(Pageable pageable, StudyTitle studyTitle) {
        List<Study> contents = queryFactory.selectFrom(study)
                .where(study.title.title.like(studyTitle.getTitle() + "%").and(study.deleted.eq(FALSE)))
                .offset(pageable.getOffset())
                .limit(FIXED_PAGE_SIZE)
                .fetch();

        if (pageable.getPageNumber() == FIRST_PAGE) {
            return new PageImpl<>(contents, pageable, contents.size());
        }

        JPAQuery<Long> totalCount = queryFactory.select(Wildcard.count)
                .where(study.deleted.eq(FALSE))
                .from(study);

        return PageableExecutionUtils.getPage(contents, pageable, () -> totalCount.fetch().get(0));
    }

    public boolean exist(StudyId studyId) {
        Integer result = queryFactory.selectOne()
                .from(study)
                .where(study.studyId.eq(studyId.getStudyId()).and(study.deleted.eq(FALSE)))
                .fetchFirst();
        return result != null;
    }

    public LikeClicked exist(MemberId memberId, StudyId studyId) {
        Integer result = queryFactory.selectOne()
                .from(like)
                .where(like.study.studyId.eq(studyId.getStudyId()).and(like.memberId.memberId.eq(memberId.getMemberId())))
                .fetchOne();
        return result != null ? LikeClicked.TRUE : LikeClicked.FALSE;
    }

    public Optional<Study> findStudyAndStudyMembersById(StudyId studyId) {
        return Optional.ofNullable(queryFactory.selectFrom(study)
                .leftJoin(study.studyMembers.studyMembers, studyMember)
                .fetchJoin()
                .where(study.studyId.eq(studyId.getStudyId()).and(study.deleted.eq(FALSE)))
                .fetchOne());
    }

    public Optional<StudyJoinRequest> findStudyJoinRequestById(StudyJoinRequestId studyJoinRequestId) {
        return Optional.ofNullable(queryFactory.selectFrom(studyJoinRequest)
                .where(studyJoinRequest.studyJoinRequestId.eq(studyJoinRequestId.getStudyJoinRequestId()))
                .fetchOne());
    }

    public Page<StudyHashTag> findStudiesByTagName(HashTagIds hashTagIds, Pageable pageable) {
        final List<StudyHashTag> contents = queryFactory.selectFrom(studyHashTag)
                .join(studyHashTag.study, study)
                .on(study.deleted.eq(FALSE))
                .where(studyHashTag.hashTag.hashTagId.in(hashTagIds.getHashTagIds()))
                .offset(pageable.getOffset())
                .limit(FIXED_PAGE_SIZE)
                .fetch();

        JPAQuery<Long> totalCount = queryFactory.select(Wildcard.count)
                .where(study.deleted.eq(FALSE))
                .from(study);

        return PageableExecutionUtils.getPage(contents, pageable, () -> totalCount.fetch().get(0));
    }

    public Optional<StudyMember> findStudyLeaderByStudyId(StudyId studyIdAsValue) {
        return Optional.ofNullable(queryFactory.selectFrom(studyMember)
                .join(studyMember.member, member)
                .fetchJoin()
                .where(studyMember.study.studyId.eq(studyIdAsValue.getStudyId()).and(studyMember.studyMemberRole.eq(LEADER)))
                .fetchOne());
    }
}
