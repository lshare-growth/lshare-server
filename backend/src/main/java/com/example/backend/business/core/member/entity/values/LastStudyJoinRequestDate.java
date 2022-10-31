package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Embeddable
public class LastStudyJoinRequestDate {

    private static final int ONE_HOUR = 1;

    private LocalDateTime lastStudyJoinRequestDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LastStudyJoinRequestDate() {
        this.lastStudyJoinRequestDate = LocalDateTime.now();
    }

    public static LastStudyJoinRequestDate initStudyJoinRequestDate() {
        return new LastStudyJoinRequestDate();
    }

    public boolean isUnderHour() {
        return this.lastStudyJoinRequestDate.isBefore(this.lastStudyJoinRequestDate.plus(1, ChronoUnit.HOURS));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastStudyJoinRequestDate)) return false;
        LastStudyJoinRequestDate that = (LastStudyJoinRequestDate) o;
        return lastStudyJoinRequestDate.equals(that.lastStudyJoinRequestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastStudyJoinRequestDate);
    }

    @Override
    public String toString() {
        return lastStudyJoinRequestDate.toString();
    }
}
