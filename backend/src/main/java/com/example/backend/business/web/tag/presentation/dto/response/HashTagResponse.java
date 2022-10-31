package com.example.backend.business.web.tag.presentation.dto.response;

import com.example.backend.business.core.study.entity.StudyHashTag;
import com.example.backend.business.core.tag.entity.TopSearchedHashTag;

public class HashTagResponse {

    private final Long hashTagId;
    private final String tagName;

    public HashTagResponse(Long hashTagId, String tagName) {
        this.hashTagId = hashTagId;
        this.tagName = tagName;
    }

    public HashTagResponse(TopSearchedHashTag topSearchedHashTag) {
        this.hashTagId = topSearchedHashTag.getHashTagId();
        this.tagName = topSearchedHashTag.getTagName();
    }

    public HashTagResponse(StudyHashTag studyHashTag) {
        this.hashTagId = studyHashTag.getHashTagId();
        this.tagName = studyHashTag.getHashTagName();
    }

    public Long getHashTagId() {
        return hashTagId;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return String.format("해시태그 아이디: %s, 태그명: %s", hashTagId, tagName);
    }
}
