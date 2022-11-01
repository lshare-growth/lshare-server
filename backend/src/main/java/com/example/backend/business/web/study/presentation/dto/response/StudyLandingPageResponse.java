package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.business.web.common.page.CustomSlice;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.Collections;
import java.util.List;

public class StudyLandingPageResponse {

    private final CustomSlice<StudyLandingResponse> contents;
    private final List<StudyHashTagResponse> hashTags;

    public StudyLandingPageResponse(Slice<StudyLandingResponse> page) {
        this.contents = convertToCustomSlice(page);
        this.hashTags = null;
    }

    public StudyLandingPageResponse(Slice<StudyLandingResponse> page, List<StudyHashTagResponse> hashTags) {
        this.contents = convertToCustomSlice(page);
        this.hashTags = hashTags;
    }

    private CustomSlice<StudyLandingResponse> convertToCustomSlice(Slice<StudyLandingResponse> page) {
        return new CustomSlice<>(page);
    }

    public static StudyLandingPageResponse emptyPage() {
        return new StudyLandingPageResponse(new SliceImpl<>(Collections.emptyList()));
    }

    public static StudyLandingPageResponse of(Slice<StudyLandingResponse> page, List<StudyHashTagResponse> hashTags) {
        return new StudyLandingPageResponse(page, hashTags);
    }

    public CustomSlice<StudyLandingResponse> getContents() {
        return contents;
    }

    public List<StudyHashTagResponse> getHashTags() {
        return hashTags;
    }

    @Override
    public String toString() {
        return String.format("스터디 랜딩페이지: %s", contents);
    }
}
