package com.example.backend.business.core.member.infrastructure.member.command;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.example.backend.business.core.member.entity.QFollow.follow;

@Repository
public class MemberQueryDslCommandRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public MemberQueryDslCommandRepository(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    public Member save(Member member) {
        entityManager.persist(member);
        return entityManager.find(Member.class, member.getMemberId());
    }

    public void unfollow(MemberId sourceId, MemberId targetId) {
        queryFactory.delete(follow)
                .where(follow.source.memberId.eq(sourceId.getMemberId()).and(follow.target.memberId.eq(targetId.getMemberId())))
                .execute();
    }
}
