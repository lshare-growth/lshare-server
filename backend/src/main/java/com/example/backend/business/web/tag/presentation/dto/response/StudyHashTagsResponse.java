package com.example.backend.business.web.tag.presentation.dto.response;

import java.util.List;

public class StudyHashTagsResponse {

    private List<HashTagResponse> hashTagResponses;

    public StudyHashTagsResponse(List<HashTagResponse> hashTagResponses) {
        this.hashTagResponses = hashTagResponses;
    }

    public static StudyHashTagsResponse of(List<HashTagResponse> hashTagResponses) {
        return new StudyHashTagsResponse(hashTagResponses);
    }

    public List<HashTagResponse> getHashTagResponses() {
        return hashTagResponses;
    }

    @Override
    public String toString() {
        return String.format("태그 리스트: %s", hashTagResponses);
    }
}
