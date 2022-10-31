package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

public class StudySearchResponse {

    private final CustomPage<StudyPageResponse> pages;
    private final List<StudyHashTagResponse> hashTags;

    public StudySearchResponse(Page<StudyPageResponse> pages) {
        this.pages = CustomPage.from(pages);
        this.hashTags = Collections.emptyList();
    }

    public StudySearchResponse(Page<StudyPageResponse> pages, List<StudyHashTagResponse> hashTags) {
        this.pages = CustomPage.from(pages);
        this.hashTags = hashTags;
    }

    public static StudySearchResponse emptyPage() {
        return new StudySearchResponse(new PageImpl<>(Collections.emptyList()));
    }

    public static StudySearchResponse of(Page<StudyPageResponse> pages, List<StudyHashTagResponse> hashTags) {
        return new StudySearchResponse(pages, hashTags);
    }

    public CustomPage<StudyPageResponse> getPages() {
        return pages;
    }

    public List<StudyHashTagResponse> getHashTags() {
        return hashTags;
    }
}
