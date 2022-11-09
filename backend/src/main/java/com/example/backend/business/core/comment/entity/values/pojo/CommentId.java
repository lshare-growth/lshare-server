package com.example.backend.business.core.comment.entity.values.pojo;

import com.example.backend.business.core.common.ErrorField;

import java.util.Objects;

public class CommentId {

    private final Long commentId;

    private CommentId(Long commentId) {
        validateCommentId(commentId);
        this.commentId = commentId;
    }

    private void validateCommentId(Long commentId) {
        if (Objects.isNull(commentId)) {
            throw new IllegalArgumentException();
        }
        if (commentId <= 0) {
            throw new IllegalArgumentException("댓글 아이디는 0 또는 음수일 수 없습니다.", ErrorField.of("commentId", commentId));
        }
    }

    public static CommentId from(Long commentId) {
        return new CommentId(commentId);
    }

    public Long getCommentId() {
        return commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentId)) return false;
        CommentId commentId1 = (CommentId) o;
        return getCommentId().equals(commentId1.getCommentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId());
    }

    @Override
    public String toString() {
        return commentId.toString();
    }
}
