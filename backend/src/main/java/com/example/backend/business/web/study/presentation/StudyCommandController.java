package com.example.backend.business.web.study.presentation;

import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyStatus;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.business.web.study.facade.StudyCommandFacade;
import com.example.backend.business.web.study.presentation.dto.request.StudyRegisterRequest;
import com.example.backend.business.web.study.presentation.dto.request.StudyStatusUpdateRequest;
import com.example.backend.business.web.study.presentation.dto.request.StudyUpdateRequest;
import com.example.backend.business.web.study.presentation.dto.response.StudyCreateResponse;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/studies")
public class StudyCommandController {

    private final StudyCommandFacade studyCommandFacade;

    @PostMapping
    public ResponseEntity<StudyCreateResponse> createStudy(@Authenticated AuthenticatedMember authenticatedMember,
                                                           @RequestBody StudyRegisterRequest request) {

        Study newStudy = studyCommandFacade.createStudy(
                authenticatedMember.getAuthenticatedIdAsValue(),
                request.toEntity(),
                TagNames.from(request.getHashTags())
        );

        return new ResponseEntity<>(StudyCreateResponse.of(newStudy), CREATED);
    }

    @PutMapping("/{studyId}")
    public ResponseEntity<Void> updateStudy(@Authenticated AuthenticatedMember authenticatedMember,
                                            @PathVariable Long studyId,
                                            @RequestBody StudyUpdateRequest request) {

        studyCommandFacade.updateStudy(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(studyId),
                StudyTitle.from(request.getTitle()),
                StudyContent.from(request.getContent()),
                TagNames.from(request.getHashTags()),
                ProgressOfStudy.findProcessByType(request.getProgressOfStudy()),
                request.getDistrictAsEnum(),
                MaxStudyMemberCount.from(request.getMaxStudyMemberCount()),
                Milestone.from(request.getStartDate(), request.getEndDate())
        );

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studyId}/study-status")
    public ResponseEntity<Void> updateStudyStatus(@Authenticated AuthenticatedMember authenticatedMember,
                                                  @PathVariable Long studyId,
                                                  @RequestBody StudyStatusUpdateRequest request) {

        studyCommandFacade.updateStudyStatus(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(studyId),
                StudyStatus.findStudyStatus(request.getStudyStatus())
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studyId}")
    public ResponseEntity<Void> deleteStudy(@Authenticated AuthenticatedMember authenticatedMember,
                                            @PathVariable Long studyId) {

        studyCommandFacade.deleteStudy(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(studyId)
        );

        return ResponseEntity.noContent().build();
    }
}
