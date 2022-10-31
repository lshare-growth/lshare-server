package com.example.backend.business.web.tag.application;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.pojo.StudyIds;
import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.example.backend.business.core.tag.infrastructure.command.HashTagQueryDslCommandRepositorys;
import com.example.backend.business.web.tag.presentation.dto.response.HashTagResponse;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashTagQueryService {

    private final HashTagQueryDslCommandRepositorys hashTagQueryDslCommandRepositorys;

    @Transactional(readOnly = true)
    public Optional<HashTag> findHashTagByTagName(TagName tagName) {
        return hashTagQueryDslCommandRepositorys.findHashTagByTagName(tagName);
    }

    @Transactional(readOnly = true)
    public List<HashTag> findHashTagByTagNames(TagName tagName, Pageable pageable) {
        return hashTagQueryDslCommandRepositorys.findHashTagsByName(tagName, pageable);
    }

    @Transactional(readOnly = true)
    public List<HashTagResponse> findTopSearchedHashTags() {
        return hashTagQueryDslCommandRepositorys.findTopSearchedHashTags();
    }

    @Transactional(readOnly = true)
    public boolean exist(TagName newTagName) {
        return hashTagQueryDslCommandRepositorys.existsHashTagByTagName(newTagName);
    }

    @Transactional(readOnly = true)
    public List<HashTagResponse> findStudyHashTagsById(StudyId studyId) {
        return hashTagQueryDslCommandRepositorys.findStudyHashTagsById(studyId);
    }

    @Transactional(readOnly = true)
    public List<StudyHashTagResponse> findStudyHashTagsByStudyIds(StudyIds studyIds) {
        return hashTagQueryDslCommandRepositorys.findHashTagsByStudyId(studyIds);
    }
}
