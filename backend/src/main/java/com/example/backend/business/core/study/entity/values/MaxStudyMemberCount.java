package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MaxStudyMemberCount {

    private static final int MIN_STUDY_MEMBER_COUNT = 1;
    private static final int MAX_STUDY_MEMBER_COUNT = 999;

    private int maxStudyMemberCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected MaxStudyMemberCount() {
    }

    private MaxStudyMemberCount(int maxStudyMemberCount) {
        validateMaxStudyMemberCount(maxStudyMemberCount);
        this.maxStudyMemberCount = maxStudyMemberCount;
    }

    private void validateMaxStudyMemberCount(int maxStudyMemberCount) {
        if (maxStudyMemberCount < MIN_STUDY_MEMBER_COUNT) {
            throw new IllegalArgumentException("스터디 인원은 최소 한 명 이상이어야 합니다.");
        }
        if (maxStudyMemberCount > MAX_STUDY_MEMBER_COUNT) {
            throw new IllegalArgumentException("스터디 최대 인원은 999명 이하로 입력해주세요.");
        }
    }

    public static MaxStudyMemberCount from(int maxStudyMemberCount) {
        return new MaxStudyMemberCount(maxStudyMemberCount);
    }

    public int getMaxStudyMemberCount() {
        return maxStudyMemberCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaxStudyMemberCount)) return false;
        MaxStudyMemberCount that = (MaxStudyMemberCount) o;
        return maxStudyMemberCount == that.maxStudyMemberCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxStudyMemberCount);
    }

    @Override
    public String toString() {
        return String.valueOf(maxStudyMemberCount);
    }
}
