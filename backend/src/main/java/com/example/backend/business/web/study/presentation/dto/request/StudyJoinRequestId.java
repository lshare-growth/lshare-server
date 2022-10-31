package com.example.backend.business.web.study.presentation.dto.request;

public class StudyJoinRequestId {

    private final Long studyJoinRequestId;

    private StudyJoinRequestId(Long studyJoinRequestId) {
        this.studyJoinRequestId = studyJoinRequestId;
    }

    public static StudyJoinRequestId from(Long studyJoinRequestId) {
        return new StudyJoinRequestId(studyJoinRequestId);
    }

    public Long getStudyJoinRequestId() {
        return studyJoinRequestId;
    }

    @Override
    public String toString() {
        return String.format("스터디 요청 아이디: %s", studyJoinRequestId);
    }
}
