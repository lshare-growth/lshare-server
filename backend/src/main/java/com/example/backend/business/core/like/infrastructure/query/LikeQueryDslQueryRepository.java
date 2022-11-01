package com.example.backend.business.core.like.infrastructure.query;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.example.backend.business.core.like.entity.QLike.like;

@Repository
public class LikeQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public LikeQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public LikeClicked exist(MemberId memberId, StudyId studyId) {
        Integer result = queryFactory.selectOne()
                .from(like)
                .where(
                        like.study.studyId.eq(studyId.getStudyId())
                                .and(like.memberId.eq(memberId))
                )
                .fetchFirst();
        return result != null ? LikeClicked.TRUE : LikeClicked.FALSE;
    }
}
