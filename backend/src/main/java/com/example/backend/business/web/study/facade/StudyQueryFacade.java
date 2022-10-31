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
import com.example.backend.business.web.study.presentation.dto.response.StudyPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyPageResponseV2;
import com.example.backend.business.web.study.presentation.dto.response.StudyResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudySearchResponse;
import com.example.backend.business.web.tag.application.HashTagQueryService;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.exception.study.StudyTypeException;
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

@Component
@RequiredArgsConstructor
public class StudyQueryFacade {

    private final StudyQueryServices studyQueryServices;
    private final StudyCommandService studyCommandService;
    private final HashTagQueryService hashTagQueryService;

    @Transactional(readOnly = true)
    public StudyLandingPageResponse findStudyLandingPage(Pageable pageable) {
        Slice<StudyPageResponse> studyLandingPage = studyQueryServices.findStudyLandingPageV3(pageable);

        if (studyLandingPage.isEmpty()) {
            return StudyLandingPageResponse.emptyPage();
        }

        StudyIds studyIds = extractStudyIds(studyLandingPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findStudyHashTagsByStudyIds(studyIds);

        return StudyLandingPageResponse.of(studyLandingPage, hashTags);
    }

    @Transactional(readOnly = true)
    public StudyPageResponseV2 findStudyPage(Pageable pageable, SortOrder sortOrder) {
        Page<StudyPageResponse> studyPage = studyQueryServices.findStudyPage(pageable, sortOrder);

        if (studyPage.isEmpty()) {
            return StudyPageResponseV2.emptyPage();
        }

        StudyIds studyIds = extractStudyIds(studyPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findStudyHashTagsByStudyIds(studyIds);

        return StudyPageResponseV2.of(studyPage, hashTags);
    }

    @Transactional(readOnly = true)
    public StudySearchResponse findStudiesByTitle(Pageable pageable, StudyTitle studyTitle) {
        Page<StudyPageResponse> studyPage = studyQueryServices.findStudiesByTitle(pageable, studyTitle);

        if (studyPage.isEmpty()) {
            return StudySearchResponse.emptyPage();
        }

        StudyIds studyIds = extractStudyIds(studyPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findStudyHashTagsByStudyIds(studyIds);

        return StudySearchResponse.of(studyPage, hashTags);
    }

    private StudyIds extractStudyIds(Slice<StudyPageResponse> studyPage) {
        return StudyIds.from(studyPage.getContent().stream()
                .map(StudyPageResponse::getStudyId)
                .collect(Collectors.toUnmodifiableList()));
    }

    @Transactional(readOnly = true)
    public Optional<Study> findStudyById(StudyId studyId) {
        return studyQueryServices.findStudyById(studyId);
    }

    /**
     * 개설된 스터디 조회수를 1증가시키며 상세정보를 반환한다.
     * Params: memberId - 회원 아이디. 로그인 하지 않았을 경우 Null 값이 들어올 수도 있다.
     * studyId  - 스터디 아이디
     * Returns: 스터디 상세정보
     * Throws: StudyNotFoundException  - 스터디를 찾을 수 없는 경우
     * MemberNotFoundException - 스터디 리더를 찾을 수 없는 경우
     * Note: 스터디 상세정보를 조회하는 Query지만 조회수를 1증가시키는 로직이 존재하기 때문에 @Transactional(readOnly = true)가
     * 아닌 @Transactional을 사용한다.
     * Since: 2022-10-13
     */
    @Transactional
    public StudyResponse findStudyByIdAndIncreaseViewCount(Long memberId, StudyId studyId) {
        Study findStudy = studyQueryServices.findStudyById(studyId).orElseThrow(() -> new BusinessException(StudyTypeException.STUDY_NOT_FOUND_EXCEPTION));
        StudyMember studyLeader = studyQueryServices.findStudyLeaderByStudyId(studyId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));

        studyCommandService.increaseViewCount(findStudy);

        return StudyResponse.of(memberId, studyLeader, findStudy);
    }

    @Transactional(readOnly = true)
    public StudySearchResponse findStudiesByTagName(TagName tagName, Pageable pageable) {
        List<HashTag> findHashTags = hashTagQueryService.findHashTagByTagNames(tagName, pageable);

        if (findHashTags.isEmpty()) {
            return StudySearchResponse.emptyPage();
        }

        HashTagIds hashTagIds = extractHashTagIds(findHashTags);
        Page<StudyPageResponse> studyPage = studyQueryServices.findStudiesByTagName(hashTagIds, pageable);

        StudyIds studyIds = extractStudyIds(studyPage);
        List<StudyHashTagResponse> hashTags = hashTagQueryService.findStudyHashTagsByStudyIds(studyIds);

        return StudySearchResponse.of(studyPage, hashTags);
    }

    private static HashTagIds extractHashTagIds(List<HashTag> findHashTags) {
        return HashTagIds.from(findHashTags.stream()
                .map(HashTag::getHashTagId)
                .collect(Collectors.toUnmodifiableList()));
    }
}
