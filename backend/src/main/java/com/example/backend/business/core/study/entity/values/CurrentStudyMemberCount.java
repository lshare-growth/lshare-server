package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CurrentStudyMemberCount {

    private int currentStudyMemberCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected CurrentStudyMemberCount() {
    }

    public CurrentStudyMemberCount(int currentStudyMemberCount) {
        validateStudyMemberCount(currentStudyMemberCount);
        this.currentStudyMemberCount = currentStudyMemberCount;
    }

    private void validateStudyMemberCount(int maxMemberCount) {
        if (maxMemberCount < 0) {
            throw new IllegalArgumentException("스터디원은 최소 1명보다 많거나 같아야 합니다.");
        }
    }

    public static CurrentStudyMemberCount from(int maxMemberCount) {
        return new CurrentStudyMemberCount(maxMemberCount);
    }

    public int getCurrentStudyMemberCount() {
        return currentStudyMemberCount;
    }

    public CurrentStudyMemberCount increaseAndGetMemberCount() {
        return new CurrentStudyMemberCount(currentStudyMemberCount + 1);
    }

    public CurrentStudyMemberCount update(int value) {
        return new CurrentStudyMemberCount(currentStudyMemberCount + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentStudyMemberCount)) return false;
        CurrentStudyMemberCount that = (CurrentStudyMemberCount) o;
        return getCurrentStudyMemberCount() == that.getCurrentStudyMemberCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentStudyMemberCount());
    }

    @Override
    public String toString() {
        return String.valueOf(currentStudyMemberCount);
    }
}
