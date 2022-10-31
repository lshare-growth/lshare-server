package com.example.backend.business.core.image.infrastructure.command;

import com.example.backend.business.core.image.entity.Image;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageQueryDslCommandRepository {

    private final JPAQueryFactory queryFactory;

    public ImageQueryDslCommandRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Image> findStudyImagesById(Long studyId) {
        return null;
    }

    private BooleanExpression eqStudyId(Long studyId) {
        return null;
    }

    private BooleanExpression deletedFalse() {
        return null;
    }
}
