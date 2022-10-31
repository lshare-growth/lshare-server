package com.example.backend.business.web.comment.presentation.dto.response;

import com.example.backend.business.core.comment.entity.Comment;

public class CommentWriteResponse {

    public final Long commentId;

    private CommentWriteResponse(Comment comment) {
        this.commentId = comment.getCommentId();
    }

    public static CommentWriteResponse of(Comment comment) {
        return new CommentWriteResponse(comment);
    }

    public Long getCommentId() {
        return commentId;
    }

    @Override
    public String toString() {
        return String.format("작성된 댓글 아이디: %s", commentId);
    }
}
