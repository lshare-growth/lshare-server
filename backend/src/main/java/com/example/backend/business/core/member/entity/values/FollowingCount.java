package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class FollowingCount {

    private static final int ZERO = 0;

    private int followingCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected FollowingCount() {
        this.followingCount = ZERO;
    }

    public static FollowingCount initFollowingCount() {
        return new FollowingCount();
    }

    private FollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public static FollowingCount from(int followingCount) {
        return new FollowingCount(followingCount);
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public Integer increaseAndGet() {
        return followingCount + 1;
    }

    public Integer decreaseAndGet() {
        int afterUnfollowCount = followingCount - 1;
        if (afterUnfollowCount < 0) {
            throw new IllegalStateException();
        }
        return afterUnfollowCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowingCount)) return false;
        FollowingCount that = (FollowingCount) o;
        return followingCount == that.followingCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followingCount);
    }

    @Override
    public String toString() {
        return String.valueOf(followingCount);
    }
}
