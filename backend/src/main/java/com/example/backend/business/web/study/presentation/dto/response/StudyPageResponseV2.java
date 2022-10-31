package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

public class StudyPageResponseV2 {


    private final CustomPage<StudyPageResponse> studies;
    private final List<StudyHashTagResponse> hashTags;

    private StudyPageResponseV2(Page<StudyPageResponse> studies) {
        this.studies = CustomPage.from(studies);
        this.hashTags = Collections.emptyList();
    }

    private StudyPageResponseV2(Page<StudyPageResponse> studies, List<StudyHashTagResponse> hashTags) {
        this.studies = CustomPage.from(studies);
        this.hashTags = hashTags;
    }

    public static StudyPageResponseV2 emptyPage() {
        return new StudyPageResponseV2(new PageImpl<>(Collections.emptyList()));
    }

    public static StudyPageResponseV2 of(Page<StudyPageResponse> studyPage, List<StudyHashTagResponse> hashTags) {
        return new StudyPageResponseV2(studyPage, hashTags);
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
