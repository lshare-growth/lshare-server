package com.example.backend.business.web.study.facade;

import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyMember;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.study.entity.values.pojo.StudyIds;
import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.entity.values.HashTagIds;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.business.web.study.application.StudyQueryServices;
import com.example.backend.business.web.study.presentation.dto.response.StudyLandingPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyLandingResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudySearchPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudySearchResponse;
import com.example.backend.business.web.tag.application.HashTagQueryService;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.mapper.database.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.study.StudyTypeException.STUDY_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class StudyQueryFacade {

    private final StudyQueryServices studyQueryServices;
    private final StudyCommandService studyCommandService;
    private final HashTagQueryService hashTagQueryService;

    @Transactional(readOnly = true)
    public Optional<Study> findById(StudyId studyId) {
        return studyQueryServices.findById(studyId);
    }

    @Transactional
    public StudyResponse findByIdAndIncreaseViewCount(Long memberId, StudyId studyId) {
        Study findStudy = studyQueryServices.findById(studyId).orElseThrow(() -> new BusinessException(STUDY_NOT_FOUND_EXCEPTION));
        StudyMember studyLeader = studyQueryServices.findStudyLeaderById(studyId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));

        studyCommandService.increaseViewCount(findStudy);

        return StudyResponse.of(memberId, studyLeader, findStudy);
    }

    @Transactional(readOnly = true)
    public StudyLandingPageResponse findLandingPage(Pageable pageable) {
        Slice<StudyLandingResponse> studyLandingPage = studyQueryServices.findLandingPage(pageable);

        if (studyLandingPage.isEmpty()) {
            return StudyLandingPageResponse.emptyPage();
        }

        StudyIds studyIds = extractStudyIds(studyLandingPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findByIds(studyIds);

        return StudyLandingPageResponse.of(studyLandingPage, hashTags);
    }

    @Transactional(readOnly = true)
    public StudyPageResponse findStudyPage(Pageable pageable, SortOrder sortOrder) {
        Page<StudySearchResponse> studyPage = studyQueryServices.findStudyPage(pageable, sortOrder);

        if (studyPage.isEmpty()) {
            return StudyPageResponse.emptyPage();
        }

        StudyIds studyIds = extractStudyIds(studyPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findByIds(studyIds);

        return StudyPageResponse.of(studyPage, hashTags);
    }

    @Transactional(readOnly = true)
    public StudySearchPageResponse searchByTitle(StudyTitle studyTitle, Pageable pageable) {
        Page<StudySearchResponse> studyPage = studyQueryServices.searchByTitle(studyTitle, pageable);

        if (studyPage.isEmpty()) {
            return StudySearchPageResponse.emptyPage();
        }

        StudyIds studyIds = extractStudyIds(studyPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findByIds(studyIds);

        return StudySearchPageResponse.of(studyPage, hashTags);
    }

    @Transactional(readOnly = true)
    public StudySearchPageResponse searchByTagName(TagName tagName, Pageable pageable) {
        List<HashTag> findHashTags = hashTagQueryService.findByTagName(tagName, pageable);

        if (findHashTags.isEmpty()) {
            return StudySearchPageResponse.emptyPage();
        }

        HashTagIds hashTagIds = extractHashTagIds(findHashTags);
        Page<StudySearchResponse> studyPage = studyQueryServices.searchByTagName(hashTagIds, pageable);

        StudyIds studyIds = extractStudyIds(studyPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findByIds(studyIds);

        return StudySearchPageResponse.of(studyPage, hashTags);
    }

    private StudyIds extractStudyIds(Slice<StudyLandingResponse> studyPage) {
        return StudyIds.from(studyPage.getContent().stream()
                .map(StudyLandingResponse::getStudyId)
                .collect(Collectors.toUnmodifiableList()));
    }

    private StudyIds extractStudyIds(Page<StudySearchResponse> studyPage) {
        return StudyIds.from(studyPage.getContent().stream()
                .map(StudySearchResponse::getStudyId)
                .collect(Collectors.toUnmodifiableList()));
    }

    private static HashTagIds extractHashTagIds(List<HashTag> findHashTags) {
        return HashTagIds.from(findHashTags.stream()
                .map(HashTag::getHashTagId)
                .collect(Collectors.toUnmodifiableList()));
    }
}
