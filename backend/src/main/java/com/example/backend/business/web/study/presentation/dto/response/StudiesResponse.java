package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class StudiesResponse {

    private final CustomPage<StudyPageResponse> studies;
    private final List<StudyHashTagResponse> hashTags;

    private StudiesResponse(Page<StudyPageResponse> studies) {
        this.studies = CustomPage.from(studies);
        this.hashTags = null;
    }

    private StudiesResponse(Page<StudyPageResponse> studies, List<StudyHashTagResponse> hashTags) {
        this.studies = CustomPage.from(studies);
        this.hashTags = hashTags;
    }

    public static StudiesResponse of(Page<StudyPageResponse> contents) {
        return new StudiesResponse(contents);
    }

    public static StudiesResponse of(Page<StudyPageResponse> studyPage, List<StudyHashTagResponse> hashTags) {
        return new StudiesResponse(studyPage, hashTags);
    }

    public CustomPage<StudyPageResponse> getStudies() {
        return studies;
    }

    public List<StudyHashTagResponse> getHashTags() {
        return hashTags;
    }

    @Override
    public String toString() {
        return String.format("현재 페이지: %s, 게시물 개수: %s", studies.getCurrentPageNumber(), studies.getTotalElementSize());
    }
}
