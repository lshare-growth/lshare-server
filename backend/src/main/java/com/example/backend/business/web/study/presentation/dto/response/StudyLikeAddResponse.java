package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.core.study.entity.Study;

public class StudyLikeAddResponse {

    private final Long studyId;

    private StudyLikeAddResponse(Study study) {
        this.studyId = study.getStudyId();
    }

    public static StudyLikeAddResponse of(Study study) {
        return new StudyLikeAddResponse(study);
    }

    public Long getStudyId() {
        return studyId;
    }
}
