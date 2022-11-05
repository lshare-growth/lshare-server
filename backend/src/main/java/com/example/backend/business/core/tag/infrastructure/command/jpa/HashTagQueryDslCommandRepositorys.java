package com.example.backend.business.core.tag.infrastructure.command.jpa;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.pojo.StudyIds;
import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.example.backend.business.web.tag.presentation.dto.response.HashTagResponse;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.backend.business.core.study.entity.QStudy.study;
import static com.example.backend.business.core.study.entity.QStudyHashTag.studyHashTag;
import static com.example.backend.business.core.tag.entity.QHashTag.hashTag;
import static com.example.backend.business.core.tag.entity.QTopSearchedHashTag.topSearchedHashTag;

@Repository
public class HashTagQueryDslCommandRepositorys {

    private final JPAQueryFactory queryFactory;

    public HashTagQueryDslCommandRepositorys(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<HashTagResponse> findById(StudyId studyId) {
        return queryFactory.select(
                        Projections.constructor(HashTagResponse.class,
                                studyHashTag.hashTag.hashTagId,
                                hashTag.tagName.tagName
                        )
                )
                .from(studyHashTag)
                .join(studyHashTag.hashTag, hashTag)
                .where(studyHashTag.study.studyId.eq(studyId.getStudyId()))
                .fetch();
    }

    public Optional<HashTag> findByTagName(TagName tagName) {
        return Optional.ofNullable(queryFactory.selectFrom(hashTag)
                .where(hashTag.tagName.eq(tagName))
                .fetchFirst());
    }

    public List<HashTag> findByTagName(TagName tagName, Pageable pageable) {
        return queryFactory.selectFrom(hashTag)
                .where(hashTag.tagName.tagName.like(tagName.getTagName() + "%"))
                .offset(pageable.getOffset())
                .limit(8)
                .fetch();
    }

    public List<StudyHashTagResponse> findByIds(StudyIds studyIds) {
        return queryFactory.select(
                        Projections.constructor(StudyHashTagResponse.class,
                                hashTag.hashTagId,
                                studyHashTag.study.studyId,
                                hashTag.tagName.tagName
                        )
                )
                .from(studyHashTag)
                .join(studyHashTag.study, study)
                .on(studyHashTag.study.studyId.in(studyIds.getStudyIds()))
                .join(studyHashTag.hashTag, hashTag)
                .on(hashTag.hashTagId.eq(studyHashTag.hashTag.hashTagId))
                .fetch();
    }

    public List<HashTagResponse> findTopSearchedHashTags() {
        return queryFactory.select(
                        Projections.constructor(HashTagResponse.class,
                                topSearchedHashTag.hashTagId.hashTagId,
                                topSearchedHashTag.tagName.tagName
                        )
                )
                .from(topSearchedHashTag)
                .fetch();
    }

    public boolean exist(TagName tagName) {
        Integer result = queryFactory.selectOne()
                .from(hashTag)
                .where(hashTag.tagName.eq(tagName))
                .fetchFirst();
        return result != null;
    }
}
