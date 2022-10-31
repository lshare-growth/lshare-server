package com.example.backend.business.web.tag.cache;

import com.example.backend.business.web.tag.presentation.dto.response.HashTagResponse;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class HashTagCache {

    private List<HashTagResponse> caches = new LinkedList<>();

    public void put(List<HashTagResponse> caches) {
        this.caches = caches;
    }

    public boolean isEmpty() {
        return caches.isEmpty();
    }

    public List<HashTagResponse> getCaches() {
        return caches;
    }
}
