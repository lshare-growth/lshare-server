package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomPage;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

public class StudySearchPageResponse {

    private final CustomPage<StudySearchResponse> pages;
    private final List<StudyHashTagResponse> hashTags;

    public StudySearchPageResponse(Page<StudySearchResponse> pages) {
        this.pages = CustomPage.from(pages);
        this.hashTags = Collections.emptyList();
    }

    public StudySearchPageResponse(Page<StudySearchResponse> pages, List<StudyHashTagResponse> hashTags) {
        this.pages = CustomPage.from(pages);
        this.hashTags = hashTags;
    }

    public static StudySearchPageResponse emptyPage() {
        return new StudySearchPageResponse(new PageImpl<>(Collections.emptyList()));
    }

    public static StudySearchPageResponse of(Page<StudySearchResponse> pages, List<StudyHashTagResponse> hashTags) {
        return new StudySearchPageResponse(pages, hashTags);
    }

    public CustomPage<StudySearchResponse> getPages() {
        return pages;
    }

    public List<StudyHashTagResponse> getHashTags() {
        return hashTags;
    }
}
