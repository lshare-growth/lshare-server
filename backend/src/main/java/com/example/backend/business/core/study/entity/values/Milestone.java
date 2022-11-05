package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Milestone {

    @Embedded
    private StartDate startDate;

    @Embedded
    private EndDate endDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected Milestone() {
    }

    private Milestone(LocalDate startDate, LocalDate endDate) {
        validateMilestone(startDate, endDate);
        this.startDate = StartDate.from(startDate);
        this.endDate = EndDate.from(endDate);
    }

    private void validateMilestone(LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            throw new IllegalArgumentException("시작일 또는 마감일이 존재하지 않습니다.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("올바른 스터디 시작일과 마감일을 입력해주세요.");
        }
    }

    public static Milestone from(LocalDate startDate, LocalDate endDate) {
        return new Milestone(startDate, endDate);
    }

    public StartDate getStartDateAsValue() {
        return startDate;
    }

    public LocalDate getStartDate() {
        return startDate.getStartDate();
    }

    public EndDate getEndDateAsValue() {
        return endDate;
    }

    public LocalDate getEndDate() {
        return endDate.getEndDate();
    }

    public boolean isAfterStudyEndDate() {
        return endDate.isAfterStudyEndDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Milestone)) return false;
        Milestone milestone = (Milestone) o;
        return startDate.equals(milestone.startDate) && endDate.equals(milestone.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return String.format("시작일: %s, 종료일: %s", startDate, endDate);
    }
}
