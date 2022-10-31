package com.example.backend.business.web.study.application;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.StudyJoinRequest;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyHashTag;
import com.example.backend.business.core.study.entity.StudyMember;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.study.infrastructure.query.StudyQueryDslQueryRepository;
import com.example.backend.business.core.tag.entity.values.HashTagIds;
import com.example.backend.business.web.study.presentation.dto.request.StudyJoinRequestId;
import com.example.backend.business.web.study.presentation.dto.response.StudyPageResponse;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.exception.study.StudyTypeException;
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

@Service
@RequiredArgsConstructor
public class StudyQueryServices {

    private final StudyQueryDslQueryRepository studyQueryDslQueryRepository;

    @Transactional(readOnly = true)
    public Slice<StudyPageResponse> findStudyLandingPageV3(Pageable pageable) {
        Slice<StudyMember> findStudyLandingPage = studyQueryDslQueryRepository.findStudyLandingPage(pageable);

        List<StudyPageResponse> studyLandingPage = findStudyLandingPage.getContent().stream()
                .map(StudyPageResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new SliceImpl<>(studyLandingPage, pageable, findStudyLandingPage.hasNext());
    }

    @Transactional(readOnly = true)
    public Page<StudyPageResponse> findStudyPage(Pageable pageable, SortOrder sortOrder) {
        Page<Study> findStudyPage = studyQueryDslQueryRepository.findStudyPage(pageable, sortOrder);

        List<StudyPageResponse> studyPage = findStudyPage.stream()
                .map(StudyPageResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(studyPage, pageable, findStudyPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<StudyPageResponse> findStudiesByTitle(Pageable pageable, StudyTitle studyTitle) {
        Page<Study> findStudyPage = studyQueryDslQueryRepository.findStudiesByTitle(pageable, studyTitle);

        List<StudyPageResponse> studyPage = findStudyPage.getContent().stream()
                .map(StudyPageResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(studyPage, pageable, findStudyPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public LikeClicked exist(MemberId memberId, StudyId studyId) {
        return studyQueryDslQueryRepository.exist(memberId, studyId);
    }

    @Transactional(readOnly = true)
    public Optional<Study> findStudyById(StudyId studyId) {
        return studyQueryDslQueryRepository.findStudyById(studyId);
    }

    @Transactional(readOnly = true)
    public Optional<Study> findStudyAndStudyMembersByStudyId(StudyId studyId) {
        return studyQueryDslQueryRepository.findStudyAndStudyMembersById(studyId);
    }

    @Transactional(readOnly = true)
    public Optional<StudyJoinRequest> findStudyJoinRequestById(StudyJoinRequestId studyJoinRequestId) {
        return studyQueryDslQueryRepository.findStudyJoinRequestById(studyJoinRequestId);
    }

    @Transactional(readOnly = true)
    public Page<StudyPageResponse> findStudiesByTagName(HashTagIds hashTagIds, Pageable pageable) {
        Page<StudyHashTag> findStudyPage = studyQueryDslQueryRepository.findStudiesByTagName(hashTagIds, pageable);

        List<StudyPageResponse> content = findStudyPage.getContent().stream()
                .map(StudyPageResponse::new)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(content, pageable, findStudyPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Optional<StudyMember> findStudyLeaderByStudyId(StudyId studyIdAsValue) {
        return studyQueryDslQueryRepository.findStudyLeaderByStudyId(studyIdAsValue);
    }

    @Transactional(readOnly = true)
    public void existStudy(StudyId studyId) {
        boolean studyExist = studyQueryDslQueryRepository.exist(studyId);
        if (!studyExist) {
            throw new BusinessException(StudyTypeException.STUDY_NOT_FOUND_EXCEPTION);
        }
    }
}
