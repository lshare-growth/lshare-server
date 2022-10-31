package com.example.backend.business.core.member.infrastructure.member.query;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.presentation.member.dto.response.FollowHistoryExistResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.backend.business.core.common.Deleted.FALSE;
import static com.example.backend.business.core.member.entity.QFollow.follow;
import static com.example.backend.business.core.member.entity.QMember.member;
import static com.example.backend.business.core.study.entity.QStudyMember.studyMember;
import static com.example.backend.business.core.study.entity.StudyMemberRole.LEADER;

@Repository
public class MemberQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Member> findById(MemberId memberId) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.memberId.eq(memberId.getMemberId()).and(member.deleted.eq(FALSE)))
                .fetchOne());
    }

    public Optional<Member> findByGithubId(GithubId githubId) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.githubId.eq(githubId).and(member.deleted.eq(FALSE)))
                .fetchOne());
    }

    public Optional<Member> findByNickName(NickName nickName) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.nickName.eq(nickName).and(member.deleted.eq(FALSE)))
                .fetchOne());
    }

    public Boolean validatePageAuthorizationByMemberIdAndStudyId(Long memberId, Long studyId) {
        if (memberId == null || studyId == null) {
            return Boolean.FALSE;
        }
        Integer result = queryFactory.selectOne()
                .from(studyMember)
                .where(studyMember.study.studyId.eq(studyId).and(studyMember.member.memberId.eq(memberId)).and(studyMember.studyMemberRole.eq(LEADER)))
                .fetchFirst();
        return result != null;
    }

    public Optional<Member> findStudyJoinRequestsById(MemberId memberId) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .fetchJoin()
                .where(member.memberId.eq(memberId.getMemberId()).and(member.deleted.eq(FALSE)))
                .fetchOne());
    }

    public FollowHistoryExistResponse findFollowHistoryById(MemberId sourceId, MemberId targetId) {
        Integer result = queryFactory.selectOne()
                .from(follow)
                .where(follow.source.memberId.eq(sourceId.getMemberId()).and(follow.target.memberId.eq(targetId.getMemberId())))
                .fetchFirst();
        return new FollowHistoryExistResponse(result);
    }

    public NickNameExistResponse validateDuplicatedNickName(NickName nickName) {
        Integer result = queryFactory.selectOne()
                .from(member)
                .where(member.nickName.eq(nickName).and(member.deleted.eq(FALSE)))
                .fetchFirst();
        return NickNameExistResponse.of(result);
    }

    public List<Follow> findFollowerListById(MemberId memberId, Cursor cursor) {
        if (cursor.isFirstPage()) {
            return queryFactory.selectFrom(follow)
                    .join(follow.source, member)
                    .fetchJoin()
                    .where(follow.target.memberId.eq(memberId.getMemberId()))
                    .limit(cursor.getPageSize() + 1)
                    .orderBy(member.memberId.desc())
                    .fetch();
        }
        return queryFactory.selectFrom(follow)
                .join(follow.source, member)
                .fetchJoin()
                .where(follow.target.memberId.eq(memberId.getMemberId())
                        .and(follow.source.memberId.lt(cursor.getNext())))
                .limit(cursor.getPageSize() + 1)
                .orderBy(member.memberId.desc())
                .fetch();
    }

    public List<Long> findFollowOrNot(MemberId memberId, List<Long> targetIds) {
        List<Follow> followerList = queryFactory.selectFrom(follow)
                .join(follow.source, member)
                .on(follow.target.memberId.in(targetIds))
                .where(follow.source.memberId.eq(memberId.getMemberId()))
                .fetch();

        if (followerList.isEmpty()) {
            return Collections.emptyList();
        }

        return followerList.stream()
                .map(Follow::getTarget)
                .map(Member::getMemberId)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Follow> findFollowingList(MemberId memberId, Cursor cursor) {
        if (cursor.isFirstPage()) {
            return queryFactory.selectFrom(follow)
                    .join(follow.target, member)
                    .fetchJoin()
                    .where(follow.source.memberId.eq(memberId.getMemberId()))
                    .limit(cursor.getPageSize() + 1)
                    .orderBy(member.memberId.desc())
                    .fetch();
        }
        return queryFactory.selectFrom(follow)
                .join(follow.target, member)
                .fetchJoin()
                .where(follow.source.memberId.lt(cursor.getNext()))
                .limit(cursor.getPageSize() + 1)
                .orderBy(member.memberId.desc())
                .fetch();
    }
}
