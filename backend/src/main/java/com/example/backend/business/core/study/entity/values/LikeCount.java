package com.example.backend.business.core.study.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LikeCount {

    private int likeCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected LikeCount() {
        this.likeCount = 0;
    }

    private LikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public static LikeCount initLikeCount() {
        return new LikeCount();
    }

    public static LikeCount from(int likeCount) {
        return new LikeCount(likeCount);
    }

    public int getLikeCount() {
        return Math.min(likeCount, 999);
    }

    public LikeCount increaseAndGet() {
        return new LikeCount(likeCount + 1);
    }

    public LikeCount decreaseAndGet() {
        int decreasedLikeCount = likeCount - 1;
        validateLikeCount(decreasedLikeCount);
        return new LikeCount(decreasedLikeCount);
    }

    private static void validateLikeCount(int likeCount) {
        if (likeCount < 0) {
            throw new IllegalStateException("좋아요 수는 음수일 수 없습니다.", ErrorField.of("likeCount", likeCount));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeCount)) return false;
        LikeCount likeCount1 = (LikeCount) o;
        return getLikeCount() == likeCount1.getLikeCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLikeCount());
    }

    @Override
    public String toString() {
        return String.valueOf(likeCount);
    }
}
