package com.example.backend.business.core.like.infrastructure.command;

import com.example.backend.business.core.like.entity.Like;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.example.backend.business.core.like.entity.QLike.like;

@Repository
public class LikeQueryDslCommandRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public LikeQueryDslCommandRepository(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    public void save(Like like) {
        entityManager.persist(like);
    }

    public void deleteLike(MemberId memberId, StudyId studyId) {
        queryFactory.delete(like)
                .where(like.study.studyId.eq(studyId.getStudyId()).and(like.memberId.eq(memberId)))
                .execute();
    }
}
