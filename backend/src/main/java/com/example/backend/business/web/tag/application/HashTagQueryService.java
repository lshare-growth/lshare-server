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
    public List<HashTagResponse> findById(StudyId studyId) {
        return hashTagQueryDslCommandRepositorys.findById(studyId);
    }

    @Transactional(readOnly = true)
    public List<StudyHashTagResponse> findByIds(StudyIds studyIds) {
        return hashTagQueryDslCommandRepositorys.findHashTagsByStudyId(studyIds);
    }

    @Transactional(readOnly = true)
    public Optional<HashTag> findByTagName(TagName tagName) {
        return hashTagQueryDslCommandRepositorys.findByTagName(tagName);
    }

    @Transactional(readOnly = true)
    public List<HashTag> findByTagName(TagName tagName, Pageable pageable) {
        return hashTagQueryDslCommandRepositorys.findByTagName(tagName, pageable);
    }

    @Transactional(readOnly = true)
    public List<HashTagResponse> findTopSearchedHashTags() {
        return hashTagQueryDslCommandRepositorys.findTopSearchedHashTags();
    }

    @Transactional(readOnly = true)
    public boolean exist(TagName tagName) {
        return hashTagQueryDslCommandRepositorys.exist(tagName);
    }
}
