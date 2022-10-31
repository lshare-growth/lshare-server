package com.example.backend.business.core.tag.entity.values;

import java.util.List;

public class HashTagIds {

    private final List<Long> hashTagIds;

    public HashTagIds(List<Long> hashTagIds) {
        this.hashTagIds = hashTagIds;
    }

    public static HashTagIds from(List<Long> hashTagIds) {
        return new HashTagIds(hashTagIds);
    }

    public List<Long> getHashTagIds() {
        return hashTagIds;
    }
}
