package com.example.backend.business.web.comment.presentation;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.comment.entity.values.pojo.CommentId;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.facade.CommentCommandFacade;
import com.example.backend.business.web.comment.presentation.dto.request.CommentReWriteRequest;
import com.example.backend.business.web.comment.presentation.dto.request.CommentUpdateRequest;
import com.example.backend.business.web.comment.presentation.dto.request.CommentWriteRequest;
import com.example.backend.business.web.comment.presentation.dto.request.ReCommentDeleteRequest;
import com.example.backend.business.web.comment.presentation.dto.request.ReCommentUpdateRequest;
import com.example.backend.business.web.comment.presentation.dto.response.CommentReWriteResponse;
import com.example.backend.business.web.comment.presentation.dto.response.CommentWriteResponse;
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
@RequestMapping("/api/comments")
public class CommentCommandController {

    private final CommentCommandFacade commentCommandFacade;

    @PostMapping
    public ResponseEntity<CommentWriteResponse> writeComment(@Authenticated AuthenticatedMember authenticatedMember,
                                                             @RequestBody CommentWriteRequest request) {

        Comment newComment = commentCommandFacade.writeComment(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(request.getStudyId()),
                CommentContent.from(request.getContent())
        );

        return new ResponseEntity<>(CommentWriteResponse.of(newComment), CREATED);
    }

    @PostMapping("/{commentId}/recomments")
    public ResponseEntity<CommentReWriteResponse> writeReComment(@Authenticated AuthenticatedMember authenticatedMember,
                                                                 @PathVariable Long commentId,
                                                                 @RequestBody CommentReWriteRequest request) {

        Comment newReComment = commentCommandFacade.writeReComment(
                authenticatedMember.getAuthenticatedIdAsValue(),
                CommentParentId.from(commentId),
                CommentContent.from(request.getContent())
        );

        return new ResponseEntity<>(CommentReWriteResponse.of(newReComment), CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@Authenticated AuthenticatedMember authenticatedMember,
                                              @PathVariable Long commentId,
                                              @RequestBody CommentUpdateRequest request) {

        commentCommandFacade.updateComment(
                authenticatedMember.getAuthenticatedIdAsValue(),
                CommentId.from(commentId),
                CommentContent.from(request.getContent())
        );

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentParentId}/recomments/{commentId}")
    public ResponseEntity<Void> updateReComment(@Authenticated AuthenticatedMember authenticatedMember,
                                                @PathVariable Long commentParentId,
                                                @PathVariable Long commentId,
                                                @RequestBody ReCommentUpdateRequest request) {

        commentCommandFacade.updateReComment(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(request.getStudyId()),
                CommentParentId.from(commentParentId),
                CommentId.from(commentId),
                CommentContent.from(request.getContent())
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@Authenticated AuthenticatedMember authenticatedMember,
                                              @PathVariable Long commentId) {

        commentCommandFacade.deleteCommentById(
                authenticatedMember.getAuthenticatedIdAsValue(),
                CommentId.from(commentId)
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{commentParentId}/recomments/{commentId}")
    public ResponseEntity<Void> deleteReComment(@Authenticated AuthenticatedMember authenticatedMember,
                                                @PathVariable Long commentParentId,
                                                @PathVariable Long commentId,
                                                @RequestBody ReCommentDeleteRequest request) {

        commentCommandFacade.deleteReComment(
                authenticatedMember.getAuthenticatedIdAsValue(),
                StudyId.from(request.getStudyId()),
                CommentParentId.from(commentParentId),
                CommentId.from(commentId)
        );

        return ResponseEntity.noContent().build();
    }
}
