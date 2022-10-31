package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RejectedCount {

    private static final int FIRST_REJECTED_COUNT = 0;
    private static final int MAX_REJECTED_COUNT = 3;

    private int rejectedCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected RejectedCount() {
        this.rejectedCount = FIRST_REJECTED_COUNT;
    }

    public static RejectedCount initRejectedCount() {
        return new RejectedCount();
    }

    private void validateRejectedCount() {
        if (this.rejectedCount > MAX_REJECTED_COUNT) {
            throw new IllegalStateException("요청 가능 횟수를 초과했습니다.");
        }
    }

    public boolean isOverThree() {
        return this.rejectedCount > MAX_REJECTED_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RejectedCount)) return false;
        RejectedCount that = (RejectedCount) o;
        return rejectedCount == that.rejectedCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rejectedCount);
    }

    @Override
    public String toString() {
        return String.valueOf(rejectedCount);
    }
}
