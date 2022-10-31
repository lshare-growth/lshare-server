package com.example.backend.business.web.study.application;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.StudyJoinRequest;
import com.example.backend.business.core.member.entity.values.StudyJoinRequestStatus;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyStatus;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.study.entity.values.pojo.studymember.StudyMemberCountUpdateStatus;
import com.example.backend.business.core.study.infrastructure.StudyJpaRepository;
import com.example.backend.business.core.study.infrastructure.command.StudyQueryDslCommandRepository;
import com.example.backend.business.core.tag.entity.values.StudyHashTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional(propagation = Propagation.NESTED)
    public void save(StudyJoinRequest studyJoinRequest) {
        studyQueryRepository.save(studyJoinRequest);
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
    public void deleteStudy(Member writer, Study findStudy) {
        findStudy.delete(writer);
    }

    @Transactional
    public void requestStudyJoin(Study study, Member member) {
        study.validateStudy(member);
        //member.validateStudyJoinRequest(StudyId.from(study.getStudyId()));

        Member studyLeader = study.findStudyLeader();

        // 새로운 멤버가 스터디 리더에게 스터디 요청
        StudyJoinRequest studyJoinRequest = StudyJoinRequest.createStudyJoinRequest(member, studyLeader, StudyId.from(study.getStudyId()));
        save(studyJoinRequest);
        //member.sendStudyJoinRequest(studyJoinRequest);
    }

    @Transactional
    public void executeStudyRequest(Study study,
                                    StudyJoinRequest findStudyJoinRequest,
                                    Member studyJoinRequestMember,
                                    StudyJoinRequestStatus studyJoinRequestStatus) {

        Member studyLeader = study.findStudyLeader();
        StudyMemberCountUpdateStatus studyMemberCountUpdateStatus = studyJoinRequestStatus.getStudyMemberCountInterface();
        findStudyJoinRequest.updateStatus(studyLeader, studyJoinRequestMember, studyJoinRequestStatus);
        study.updateStudyMemberCount(studyMemberCountUpdateStatus.getStudyMemberCountUpdateValue());
    }

    @Transactional
    public void updateStudyStatus(Member member,
                                  Study study,
                                  StudyStatus studyStatus) {

        study.updateStudyStatus(member, studyStatus);
    }

    @Transactional
    public void increaseViewCount(Study study) {
        study.increaseViewCount();
    }
}
