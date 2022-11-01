package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

public class StudyPageResponse {


    private final CustomPage<StudySearchResponse> studies;
    private final List<StudyHashTagResponse> hashTags;

    private StudyPageResponse(Page<StudySearchResponse> studies) {
        this.studies = CustomPage.from(studies);
        this.hashTags = Collections.emptyList();
    }

    private StudyPageResponse(Page<StudySearchResponse> studies, List<StudyHashTagResponse> hashTags) {
        this.studies = CustomPage.from(studies);
        this.hashTags = hashTags;
    }

    public static StudyPageResponse emptyPage() {
        return new StudyPageResponse(new PageImpl<>(Collections.emptyList()));
    }

    public static StudyPageResponse of(Page<StudySearchResponse> studyPage, List<StudyHashTagResponse> hashTags) {
        return new StudyPageResponse(studyPage, hashTags);
    }

    public CustomPage<StudySearchResponse> getStudies() {
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
