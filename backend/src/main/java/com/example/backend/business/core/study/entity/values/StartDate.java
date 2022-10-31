package com.example.backend.business.core.study.entity.values;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class StartDate {

    private LocalDate startDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected StartDate() {
        this.startDate = LocalDate.now();
    }

    private StartDate(LocalDate startDate) {
        validateStartDate(startDate);
        this.startDate = startDate;
    }

    private void validateStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("올바른 스터디 시작일과 마감일을 입력해주세요.");
        }
    }

    public static StartDate from(LocalDate startDate) {
        return new StartDate(startDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartDate)) return false;
        StartDate startDate1 = (StartDate) o;
        return startDate.equals(startDate1.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate);
    }

    @Override
    public String toString() {
        return startDate.toString();
    }
}
