package com.example.backend.business.web.study.presentation.dto.request;

public class StudyStatusUpdateRequest {

    private String studyStatus;

    private StudyStatusUpdateRequest() {
    }

    public StudyStatusUpdateRequest(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    @Override
    public String toString() {
        return String.format("변경할 스터디 상태: %s", studyStatus);
    }
}
