package com.example.backend.business.web.study.application;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyHashTag;
import com.example.backend.business.core.study.entity.StudyMember;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.study.infrastructure.query.StudyQueryDslQueryRepository;
import com.example.backend.business.core.tag.entity.values.HashTagIds;
import com.example.backend.business.web.study.presentation.dto.response.StudyLandingResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudySearchResponse;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.mapper.database.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.backend.common.exception.study.StudyTypeException.STUDY_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class StudyQueryServices {

    private final StudyQueryDslQueryRepository studyQueryDslQueryRepository;

    @Transactional(readOnly = true)
    public Optional<Study> findById(StudyId studyId) {
        return studyQueryDslQueryRepository.findById(studyId);
    }

    @Transactional(readOnly = true)
    public Optional<StudyMember> findStudyLeaderById(StudyId studyId) {
        return studyQueryDslQueryRepository.findStudyLeaderById(studyId);
    }

    @Transactional(readOnly = true)
    public Slice<StudyLandingResponse> findLandingPage(Pageable pageable) {
        Slice<StudyMember> findStudyLandingPage = studyQueryDslQueryRepository.findLandingPage(pageable);

        List<StudyLandingResponse> studyLandingPage = findStudyLandingPage.getContent().stream()
                .map(StudyLandingResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new SliceImpl<>(studyLandingPage, pageable, findStudyLandingPage.hasNext());
    }

    @Transactional(readOnly = true)
    public Page<StudySearchResponse> findStudyPage(Pageable pageable, SortOrder sortOrder) {
        Page<Study> findStudyPage = studyQueryDslQueryRepository.findStudyPage(pageable, sortOrder);

        List<StudySearchResponse> studyPage = findStudyPage.stream()
                .map(StudySearchResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(studyPage, pageable, findStudyPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<StudySearchResponse> searchByTitle(StudyTitle studyTitle, Pageable pageable) {
        Page<Study> findStudyPage = studyQueryDslQueryRepository.searchByTitle(studyTitle, pageable);

        List<StudySearchResponse> studyPage = findStudyPage.getContent().stream()
                .map(StudySearchResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(studyPage, pageable, findStudyPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<StudySearchResponse> searchByTagName(HashTagIds hashTagIds, Pageable pageable) {
        Page<StudyHashTag> findStudyPage = studyQueryDslQueryRepository.searchByTagName(hashTagIds, pageable);

        List<StudySearchResponse> content = findStudyPage.getContent().stream()
                .map(StudySearchResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(content, pageable, findStudyPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public LikeClicked exist(MemberId memberId, StudyId studyId) {
        return studyQueryDslQueryRepository.exist(memberId, studyId);
    }

    @Transactional(readOnly = true)
    public void exist(StudyId studyId) {
        boolean studyExist = studyQueryDslQueryRepository.exist(studyId);
        if (!studyExist) {
            throw new BusinessException(STUDY_NOT_FOUND_EXCEPTION);
        }
    }
}
