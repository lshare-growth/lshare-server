package com.example.backend.business.web.tag.facade;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.tag.application.HashTagQueryService;
import com.example.backend.business.web.tag.cache.HashTagCache;
import com.example.backend.business.web.tag.presentation.dto.response.HashTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HashTagQueryFacade {

    private final HashTagQueryService hashTagQueryService;
    private final HashTagCache hashTagCache;

    @Transactional(readOnly = true)
    public List<HashTagResponse> findTopSearchedHashTags() {
        if (hashTagCache.isEmpty()) {
            List<HashTagResponse> response = hashTagQueryService.findTopSearchedHashTags();
            hashTagCache.put(response);
            return hashTagCache.getCaches();
        }
        return hashTagCache.getCaches();
    }

    @Transactional(readOnly = true)
    public List<HashTagResponse> findStudyHashTagsById(StudyId studyId) {
        return hashTagQueryService.findById(studyId);
    }
}
