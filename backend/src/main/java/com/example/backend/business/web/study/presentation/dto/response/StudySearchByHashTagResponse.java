package com.example.backend.business.web.study.presentation.dto.response;

import org.springframework.data.domain.Page;

public class StudySearchByHashTagResponse {

    private final Page<StudyPageByHashTagResponse> contents;

    private StudySearchByHashTagResponse (Page<StudyPageByHashTagResponse> studyPageInHashTags){
        this.contents = studyPageInHashTags;
    }

    public static StudySearchByHashTagResponse of(Page<StudyPageByHashTagResponse> studyPageInHashTags) {
        return new StudySearchByHashTagResponse(studyPageInHashTags);
    }

    public Page<StudyPageByHashTagResponse> getContents() {
        return contents;
    }
}
