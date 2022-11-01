package com.example.backend.business.web.study.application;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyStatus;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.study.infrastructure.StudyJpaRepository;
import com.example.backend.business.core.study.infrastructure.command.StudyQueryDslCommandRepository;
import com.example.backend.business.core.tag.entity.values.StudyHashTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyCommandService {

    private final StudyJpaRepository studyJpaRepository;
    private final StudyQueryDslCommandRepository studyQueryRepository;

    @Transactional
    public Study save(Study study) {
        return studyJpaRepository.save(study);
    }

    @Transactional
    public void increaseViewCount(Study study) {
        study.increaseViewCount();
    }

    @Transactional
    public void updateStudy(Member studyLeader,
                            Study study,
                            StudyTitle title,
                            StudyContent content,
                            StudyHashTags newHashTags,
                            ProgressOfStudy progressOfStudy,
                            District district,
                            MaxStudyMemberCount maxStudyMemberCount,
                            Milestone milestone) {

        study.updateStudy(studyLeader, title, content, newHashTags, progressOfStudy, district, maxStudyMemberCount, milestone);
    }

    @Transactional
    public void updateStudyStatus(Member member,
                                  Study study,
                                  StudyStatus studyStatus) {

        study.updateStudyStatus(member, studyStatus);
    }

    @Transactional
    public void deleteStudy(Member writer, Study findStudy) {
        findStudy.delete(writer);
    }
}
