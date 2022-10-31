package com.example.backend.business.web.study.presentation.dto.request;

import com.example.backend.business.core.tag.entity.values.TagNames;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SearchHashTagsRequest {

    private final List<String> hashTags = new LinkedList<>();

    private SearchHashTagsRequest() {
    }

    public SearchHashTagsRequest(String... hashTags) {
        addHashTags(hashTags);
    }

    public void addHashTags(String... hashTags) {
        this.hashTags.addAll(Arrays.asList(hashTags));
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    public TagNames getTagNames() {
        return TagNames.from(getHashTags());
    }

    @Override
    public String toString() {
        return String.format("검색 해시태그: %s", hashTags);
    }
}
