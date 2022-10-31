package com.example.backend.business.web.like.application;

import com.example.backend.business.core.like.entity.Like;
import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.like.infrastructure.command.LikeQueryDslCommandRepository;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeCommandService {

    private final LikeQueryDslCommandRepository likeQueryDslCommandRepository;

    @Transactional
    public void updateLike(LikeClicked likeClicked,
                           MemberId memberId,
                           Study study) {

        if (likeClicked.isTrue()) {
            likeQueryDslCommandRepository.deleteLike(memberId, StudyId.from(study.getStudyId()));
            study.decreaseLikeCount();
            return;
        }
        likeQueryDslCommandRepository.save(Like.update(study, memberId));
        study.increaseLikeCount();
    }
}
