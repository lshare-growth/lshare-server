package com.example.backend.business.web.tag.presentation;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.tag.facade.HashTagQueryFacade;
import com.example.backend.business.web.tag.presentation.dto.response.HashTagResponse;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtags")
public class HashTagQueryController {

    private final HashTagQueryFacade hashTagQueryFacade;

    @GetMapping("/top-searched-hashtags")
    public ResponseEntity<StudyHashTagsResponse> findTopSearchedHashTags() {

        List<HashTagResponse> hashTags = hashTagQueryFacade.findTopSearchedHashTags();

        return ResponseEntity.ok()
                .eTag("v1")
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(StudyHashTagsResponse.of(hashTags));
    }

    @GetMapping("study/{studyId}")
    public ResponseEntity<StudyHashTagsResponse> findStudyHashTagsById(@PathVariable Long studyId) {

        List<HashTagResponse> hashTags = hashTagQueryFacade.findStudyHashTagsById(
                StudyId.from(studyId)
        );

        return ResponseEntity.ok(StudyHashTagsResponse.of(hashTags));
    }
}
