package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.study.entity.Study;

public class StudyCreateResponse {

    private final Long studyId;

    private StudyCreateResponse(Study study) {
        this.studyId = study.getStudyId();
    }

    public static StudyCreateResponse of(Study study) {
        return new StudyCreateResponse(study);
    }

    public Long getStudyId() {
        return studyId;
    }

    @Override
    public String toString() {
        return String.format("개설된 스터디 아이디: %s", studyId);
    }
}
