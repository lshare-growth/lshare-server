package com.example.backend.business.web.tag.presentation.dto.response;

public class StudyHashTagResponse {

    private Long hashTagId;
    private Long studyId;
    private String tagName;

    public StudyHashTagResponse(Long hashTagId,
                                Long studyId,
                                String tagName) {

        this.hashTagId = hashTagId;
        this.studyId = studyId;
        this.tagName = tagName;
    }

    public Long getHashTagId() {
        return hashTagId;
    }

    public Long getStudyId() {
        return studyId;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return String.format("태그아이디: %s, 스터디 아이디: %s, 태그명: %s", hashTagId, studyId, tagName);
    }
}
