package com.example.backend.business.web.like.facade;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.like.application.LikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LikeQueryFacade {

    private final LikeQueryService likeQueryService;

    @Transactional(readOnly = true)
    public LikeClicked exist(MemberId memberId, StudyId studyId) {
        return likeQueryService.exist(memberId, studyId);
    }
}
