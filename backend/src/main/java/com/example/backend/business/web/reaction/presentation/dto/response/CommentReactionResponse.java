package com.example.backend.business.web.reaction.presentation.dto.response;

import com.example.backend.business.core.reaction.entity.CommentReaction;

import javax.persistence.Transient;

public class CommentReactionResponse {

    @Transient
    private final CommentReaction commentReaction;
    private final Long commentId;
    private final String emotion;
    private final Long memberId;

    public CommentReactionResponse(Long commentId, String emotion, Long memberId) {
        this.commentReaction = CommentReaction.from(commentId, emotion, memberId);
        this.commentId = commentId;
        this.emotion = emotion;
        this.memberId = memberId;
    }

    private CommentReactionResponse(CommentReaction commentReaction) {
        this.commentId = commentReaction.getCommentId();
        this.emotion = commentReaction.getEmotion();
        this.memberId = commentReaction.getMemberId();
        this.commentReaction = CommentReaction.from(commentId, emotion, memberId);
    }

    public static CommentReactionResponse of(CommentReaction commentReaction) {
        return new CommentReactionResponse(commentReaction);
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getEmotion() {
        return emotion;
    }

    public Long getMemberId() {
        return memberId;
    }

    public CommentReaction getCommentReaction() {
        return commentReaction;
    }

    @Override
    public String toString() {
        return String.format("댓글 아이디: %s, 이모티콘: %s, 회원 아이디: %s", commentId, emotion, memberId);
    }
}
