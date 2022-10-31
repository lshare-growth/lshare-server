package com.example.backend.business.web.like.application;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.like.infrastructure.query.LikeQueryDslQueryRepository;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.values.StudyId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeQueryService {

    private final LikeQueryDslQueryRepository likeQueryDslQueryRepository;

    @Transactional(readOnly = true)
    public LikeClicked exist(MemberId memberId, StudyId studyId) {
        return likeQueryDslQueryRepository.exist(memberId, studyId);
    }
}
