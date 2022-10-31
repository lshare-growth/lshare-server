package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LikeCount {

    private static final int ONE = 0;
    private static final int MAX_LIKE_COUNTS = 999;

    private int likeCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected LikeCount() {
        this.likeCount = ONE;
    }

    private LikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public static LikeCount from(int likeCount) {
        return new LikeCount(likeCount);
    }

    public static LikeCount initLikeCount() {
        return new LikeCount();
    }

    public int getLikeCount() {
        return Math.min(likeCount, MAX_LIKE_COUNTS);
    }

    public LikeCount increaseAndGet() {
        return new LikeCount(likeCount + 1);
    }

    public LikeCount decreaseAndGet() {
        return new LikeCount(likeCount - 1);
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
