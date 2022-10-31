package com.example.backend.business.web.reaction.presentation;

import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.reaction.entity.values.CommentReactionEmotion;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.reaction.facade.ReactionCommandFacade;
import com.example.backend.business.web.reaction.presentation.dto.request.ReactionUpdateRequest;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reactions")
public class ReactionCommandController {

    private final ReactionCommandFacade reactionCommandFacade;

    @PostMapping("/studies/{studyId}/comments/{commentId}")
    public ResponseEntity<Void> updateReaction(@Authenticated AuthenticatedMember authenticatedMember,
                                               @PathVariable Long studyId,
                                               @PathVariable Long commentId,
                                               @RequestBody ReactionUpdateRequest request) {

        reactionCommandFacade.updateReaction(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(studyId),
                CommentId.from(commentId),
                CommentReactionEmotion.from(request.getEmotion())
        );

        return ResponseEntity.ok().build();
    }
}
