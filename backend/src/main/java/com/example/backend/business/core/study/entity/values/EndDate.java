package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class EndDate {

    private LocalDate endDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected EndDate() {
        this.endDate = LocalDate.now();
    }

    private EndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public static EndDate from(LocalDate endDate) {
        return new EndDate(endDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isAfterStudyEndDate() {
        return LocalDate.now().isAfter(endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndDate)) return false;
        EndDate endDate1 = (EndDate) o;
        return endDate.equals(endDate1.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate);
    }

    @Override
    public String toString() {
        return endDate.toString();
    }
}
