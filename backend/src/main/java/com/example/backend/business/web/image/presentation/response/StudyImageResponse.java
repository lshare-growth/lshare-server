package com.example.backend.business.web.image.presentation.response;

import com.example.backend.business.core.image.entity.Image;

public class StudyImageResponse {

    private final Long studyImageId;
    private final String studyImageUrl;

    public StudyImageResponse(Long studyImageId, String studyImageUrl) {
        this.studyImageId = studyImageId;
        this.studyImageUrl = studyImageUrl;
    }

    public StudyImageResponse(Image image) {
        this(image.getStudyImageId(), image.getImageUrl());
    }

    public Long getStudyImageId() {
        return studyImageId;
    }

    public String getStudyImageUrl() {
        return studyImageUrl;
    }

    @Override
    public String toString() {
        return studyImageUrl;
    }
}
