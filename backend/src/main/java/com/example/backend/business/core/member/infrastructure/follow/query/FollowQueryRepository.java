package com.example.backend.business.core.member.infrastructure.follow.query;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.presentation.follow.dto.response.FollowHistoryExistResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.backend.business.core.member.entity.QFollow.follow;
import static com.example.backend.business.core.member.entity.QMember.member;

@Repository
public class FollowQueryRepository {

    private final JPAQueryFactory queryFactory;

    public FollowQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public FollowHistoryExistResponse findFollowHistoryById(MemberId sourceId, MemberId targetId) {
        Integer result = queryFactory.selectOne()
                .from(follow)
                .where(
                        follow.source.memberId.eq(sourceId.getMemberId())
                                .and(follow.target.memberId.eq(targetId.getMemberId()))
                )
                .fetchFirst();
        return new FollowHistoryExistResponse(result);
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
                .join(follow.target, member)
                .fetchJoin()
                .where(
                        follow.target.memberId.eq(memberId.getMemberId())
                                .and(follow.source.memberId.lt(cursor.getNext()))
                )
                .limit(cursor.getPageSize() + 1)
                .orderBy(member.memberId.desc())
                .fetch();
    }

    public List<Long> findFollowOrNot(MemberId memberId, List<Long> targetIds) {
        List<Follow> followerList = queryFactory.selectFrom(follow)
                .join(follow.target, member)
                .on(member.memberId.in(targetIds))
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
                .where(
                        follow.source.memberId.eq(memberId.getMemberId())
                                .and(follow.target.memberId.lt(cursor.getNext()))
                )
                .limit(cursor.getPageSize() + 1)
                .orderBy(member.memberId.desc())
                .fetch();
    }
}
