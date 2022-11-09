package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class FollowerCount {

    private static final int ZERO = 0;

    private int followerCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected FollowerCount() {
        this.followerCount = ZERO;
    }

    public static FollowerCount initFollowerCount() {
        return new FollowerCount();
    }

    private FollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public static FollowerCount from(int followerCount) {
        return new FollowerCount(followerCount);
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int increaseAndGet() {
        return followerCount + 1;
    }

    public int decraseAndGet() {
        int afterUnfollowCount = followerCount - 1;
        if (afterUnfollowCount < 0) {
            throw new IllegalStateException("팔로우 수는 음수일 수 없습니다.", ErrorField.of("followCount", followerCount));
        }
        return afterUnfollowCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowerCount)) return false;
        FollowerCount that = (FollowerCount) o;
        return followerCount == that.followerCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerCount);
    }

    @Override
    public String toString() {
        return String.valueOf(followerCount);
    }
}
