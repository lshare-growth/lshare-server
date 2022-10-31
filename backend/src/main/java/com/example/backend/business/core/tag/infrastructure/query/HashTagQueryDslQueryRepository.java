package com.example.backend.business.core.tag.infrastructure.query;

import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.entity.values.HashTagId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.example.backend.business.core.tag.entity.QHashTag.hashTag;

@Repository
public class HashTagQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public HashTagQueryDslQueryRepository(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    public HashTag save(HashTag hashTag) {
        entityManager.persist(hashTag);
        return entityManager.find(HashTag.class, hashTag.getHashTagId());
    }

    public Optional<HashTag> findById(HashTagId hashTagId) {
        return Optional.ofNullable(queryFactory.selectFrom(hashTag)
                .where(hashTag.hashTagId.eq(hashTagId.getHashTagId()))
                .fetchOne());
    }
}
