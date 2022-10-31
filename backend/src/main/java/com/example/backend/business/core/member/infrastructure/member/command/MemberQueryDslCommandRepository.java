package com.example.backend.business.core.member.infrastructure.member.command;

import com.example.backend.business.core.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
}
