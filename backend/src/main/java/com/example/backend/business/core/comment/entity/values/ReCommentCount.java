package com.example.backend.business.core.comment.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ReCommentCount {

    private static final int ZERO = 0;

    private int reCommentCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 comment 외부 패키지에서 호출하지 말 것.
     */
    protected ReCommentCount() {
        this.reCommentCount = ZERO;
    }

    public static ReCommentCount initReCommentCount() {
        return new ReCommentCount();
    }

    private ReCommentCount(int reCommentCount) {
        this.reCommentCount = reCommentCount;
    }

    public int getReCommentCount() {
        return reCommentCount;
    }

    public ReCommentCount increaseAndGetReCommentCount() {
        return new ReCommentCount(reCommentCount + 1);
    }

    public ReCommentCount decreaseAndGetReCommentCount() {
        int decreasedCommentCount = reCommentCount - 1;
        validateReCommentCount(decreasedCommentCount);
        return new ReCommentCount(decreasedCommentCount);
    }

    private static void validateReCommentCount(int decreasedCommentCount) {
        if (decreasedCommentCount < ZERO) {
            throw new IllegalStateException("대댓글수는 음수일 수 없습니다.", ErrorField.of("reCommentCount", decreasedCommentCount));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReCommentCount)) return false;
        ReCommentCount that = (ReCommentCount) o;
        return reCommentCount == that.reCommentCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reCommentCount);
    }

    @Override
    public String toString() {
        return String.valueOf(reCommentCount);
    }
}
