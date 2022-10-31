package com.example.backend.business.web.study.presentation.dto.request;

import com.example.backend.business.core.member.entity.values.StudyJoinRequestStatus;

public class StudyStatusChangeRequest {

    private Long studyJoinRequestId;
    private Long studyJoinRequestMemberId;
    private String studyJoinRequest;

    private StudyStatusChangeRequest (){
    }

    public StudyStatusChangeRequest(Long studyJoinRequestId, Long studyJoinRequestMemberId, String studyJoinRequest) {
        this.studyJoinRequestId = studyJoinRequestId;
        this.studyJoinRequestMemberId = studyJoinRequestMemberId;
        this.studyJoinRequest = studyJoinRequest;
    }

    public Long getStudyJoinRequestId() {
        return studyJoinRequestId;
    }

    public Long getStudyJoinRequestMemberId() {
        return studyJoinRequestMemberId;
    }

    public String getStudyJoinRequest() {
        return studyJoinRequest;
    }

    public StudyJoinRequestStatus getStudyJoinRequestStatusAsEnum(){
        return StudyJoinRequestStatus.findStudyJoinRequestStatus(studyJoinRequest);
    }

    @Override
    public String toString() {
        return String.format("스터디 참여신청 요청 처리: %s", studyJoinRequest);
    }
}
