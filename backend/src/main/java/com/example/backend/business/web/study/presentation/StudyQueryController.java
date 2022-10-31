package com.example.backend.business.web.study.presentation;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.example.backend.business.web.study.facade.StudyQueryFacade;
import com.example.backend.business.web.study.presentation.dto.response.StudyLandingPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyPageResponseV2;
import com.example.backend.business.web.study.presentation.dto.response.StudyResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudySearchResponse;
import com.example.backend.common.mapper.database.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.backend.common.api.ApiUtils.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/studies")
public class StudyQueryController {

    private static final String NULL_STRING = "";

    private final StudyQueryFacade studyQueryFacade;

    @GetMapping("/landing")
    public ResponseEntity<StudyLandingPageResponse> findStudyLandingPage(Pageable pageable) {

        StudyLandingPageResponse response = studyQueryFacade.findStudyLandingPage(
                pageable
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<StudyPageResponseV2> findStudyPage(Pageable pageable) {

        StudyPageResponseV2 response = studyQueryFacade.findStudyPage(
                pageable,
                SortOrder.findByType(null)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<StudyResponse> findStudyById(@PathVariable Long studyId,
                                                       HttpServletRequest httpServletRequest) {

        StudyResponse response = studyQueryFacade.findStudyByIdAndIncreaseViewCount(
                getMemberId(httpServletRequest),
                StudyId.from(studyId)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/searching")
    public ResponseEntity<StudySearchResponse> findStudiesByTitle(@RequestParam String title,
                                                                  Pageable pageable) {

        if (Objects.isNull(title) || isNullString(title)) {
            return ResponseEntity.ok(StudySearchResponse.emptyPage());
        }

        StudySearchResponse response = studyQueryFacade.findStudiesByTitle(
                pageable,
                StudyTitle.from(title)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tag-searching")
    public ResponseEntity<StudySearchResponse> findStudiesByTagName(@RequestParam String tagName,
                                                                    Pageable pageable) {

        if (Objects.isNull(tagName) || isNullString(tagName)) {
            return ResponseEntity.ok(StudySearchResponse.emptyPage());
        }

        StudySearchResponse response = studyQueryFacade.findStudiesByTagName(
                TagName.from(tagName),
                pageable
        );

        return ResponseEntity.ok(response);
    }

    private boolean isNullString(String value) {
        return value.trim().equals(NULL_STRING);
    }
}
