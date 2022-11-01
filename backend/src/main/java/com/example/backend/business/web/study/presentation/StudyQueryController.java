package com.example.backend.business.web.study.presentation;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.example.backend.business.web.study.facade.StudyQueryFacade;
import com.example.backend.business.web.study.presentation.dto.response.StudyLandingPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyPageResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudyResponse;
import com.example.backend.business.web.study.presentation.dto.response.StudySearchPageResponse;
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

    private final StudyQueryFacade studyQueryFacade;

    @GetMapping("/{studyId}")
    public ResponseEntity<StudyResponse> findById(@PathVariable Long studyId,
                                                  HttpServletRequest httpServletRequest) {

        StudyResponse response = studyQueryFacade.findByIdAndIncreaseViewCount(
                getMemberId(httpServletRequest),
                StudyId.from(studyId)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/landing")
    public ResponseEntity<StudyLandingPageResponse> findLandingPage(Pageable pageable) {

        StudyLandingPageResponse response = studyQueryFacade.findLandingPage(
                pageable
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<StudyPageResponse> findStudyPage(Pageable pageable) {

        StudyPageResponse response = studyQueryFacade.findStudyPage(
                pageable,
                SortOrder.findByType(null)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/searching")
    public ResponseEntity<StudySearchPageResponse> searchByTitle(@RequestParam String title,
                                                                 Pageable pageable) {

        if (Objects.isNull(title) || isNullString(title)) {
            return ResponseEntity.ok(StudySearchPageResponse.emptyPage());
        }

        StudySearchPageResponse response = studyQueryFacade.searchByTitle(
                StudyTitle.from(title),
                pageable
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tag-searching")
    public ResponseEntity<StudySearchPageResponse> searchByTagName(@RequestParam(required = false) String tagName,
                                                                   Pageable pageable) {

        if (Objects.isNull(tagName) || isNullString(tagName)) {
            return ResponseEntity.ok(StudySearchPageResponse.emptyPage());
        }

        StudySearchPageResponse response = studyQueryFacade.searchByTagName(
                TagName.from(tagName),
                pageable
        );

        return ResponseEntity.ok(response);
    }

    private boolean isNullString(String value) {
        return value.trim().equals("");
    }
}
