package com.example.backend.business.core.member.infrastructure.follow.command;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.example.backend.business.core.member.entity.QFollow.follow;

@Repository
public class FollowCommandRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public FollowCommandRepository(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    public void unfollow(MemberId sourceId, MemberId targetId) {
        queryFactory.delete(follow)
                .where(follow.source.memberId.eq(sourceId.getMemberId()).and(follow.target.memberId.eq(targetId.getMemberId())))
                .execute();
    }
}
