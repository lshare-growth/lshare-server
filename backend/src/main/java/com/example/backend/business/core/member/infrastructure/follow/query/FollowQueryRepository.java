package com.example.backend.business.core.member.infrastructure.follow.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FollowQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public FollowQueryRepository(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }
}
