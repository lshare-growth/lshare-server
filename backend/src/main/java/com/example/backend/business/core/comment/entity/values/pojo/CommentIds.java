package com.example.backend.business.core.comment.entity.values.pojo;

import java.util.Objects;
import java.util.Set;

public class CommentIds {

    private final Set<Long> commentIds;

    private CommentIds(Set<Long> commentIds) {
        validateCommentIds(commentIds);
        this.commentIds = commentIds;
    }

    private void validateCommentIds(Set<Long> commentIds) {
        if (Objects.isNull(commentIds) || commentIds.isEmpty()) {
            throw new IllegalArgumentException("댓글 아이디들이 존재하지 않습니다.");
        }
    }

    public static CommentIds from(Set<Long> commentIds) {
        return new CommentIds(commentIds);
    }

    public Set<Long> getCommentIds() {
        return commentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentIds)) return false;
        CommentIds that = (CommentIds) o;
        return getCommentIds().equals(that.getCommentIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentIds());
    }

    @Override
    public String toString() {
        return String.format("댓글 아이디 목록: %s", commentIds);
    }
}
