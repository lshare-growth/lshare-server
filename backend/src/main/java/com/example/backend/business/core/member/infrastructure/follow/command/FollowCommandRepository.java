package com.example.backend.business.core.member.infrastructure.follow.command;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FollowCommandRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public FollowCommandRepository(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }
}
