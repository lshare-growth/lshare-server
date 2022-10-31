package com.example.backend.business.web.image.presentation.response;

import java.util.List;

public class StudyImagesResponse {

    private final List<StudyImageResponse> studyImagesResponse;

    private StudyImagesResponse(List<StudyImageResponse> studyImagesResponse) {
        this.studyImagesResponse = studyImagesResponse;
    }

    public static StudyImagesResponse of(List<StudyImageResponse> studyImagesResponse) {
        return new StudyImagesResponse(studyImagesResponse);
    }

    public List<StudyImageResponse> getStudyImagesResponse() {
        return studyImagesResponse;
    }
}
