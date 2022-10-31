package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CommentCount {

    private static final int ZERO = 0;

    private int commentCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected CommentCount() {
        this.commentCount = ZERO;
    }

    private CommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public static CommentCount initCommentCount() {
        return new CommentCount();
    }

    public static CommentCount from(int commentCount) {
        return new CommentCount(commentCount);
    }

    public CommentCount decreaseAndGetCommentCount() {
        int decreasedCommentCount = this.commentCount - 1;
        validateCommentCount(decreasedCommentCount);
        return new CommentCount(decreasedCommentCount);
    }

    private void validateCommentCount(int commentCount) {
        if (commentCount < ZERO) {
            throw new IllegalStateException("댓글수는 음수일 수 없습니다.");
        }
    }

    public int getCommentCount() {
        return commentCount;
    }

    public CommentCount increaseAndGet() {
        return new CommentCount(commentCount + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentCount)) return false;
        CommentCount that = (CommentCount) o;
        return commentCount == that.commentCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentCount);
    }

    @Override
    public String toString() {
        return String.valueOf(commentCount);
    }
}
