package com.example.backend.business.web.like.presentation;

import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.like.facade.LikeCommandFacade;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeCommandController {

    private final LikeCommandFacade likeCommandFacade;

    @PostMapping("/study/{studyId}")
    public ResponseEntity<Void> updateLike(@Authenticated AuthenticatedMember authenticatedMember,
                                           @PathVariable Long studyId) {

        likeCommandFacade.updateLike(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(studyId)
        );

        return ResponseEntity.ok().build();
    }
}
