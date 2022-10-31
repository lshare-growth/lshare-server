package com.example.backend.business.web.comment.presentation.dto.response;

import com.example.backend.business.core.comment.entity.Comment;

public class CommentReWriteResponse {

    private final Long commentId;

    private CommentReWriteResponse(Long commentId) {
        this.commentId = commentId;
    }

    public static CommentReWriteResponse of(Comment writeReComment) {
        return new CommentReWriteResponse(writeReComment.getCommentId());
    }

    public Long getCommentId() {
        return commentId;
    }

    @Override
    public String toString() {
        return String.format("작성된 대댓글 아이디: %s", commentId);
    }
}
