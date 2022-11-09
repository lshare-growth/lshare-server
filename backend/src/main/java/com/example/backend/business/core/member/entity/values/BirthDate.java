package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class BirthDate {

    private LocalDate birthDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected BirthDate() {
    }

    private BirthDate(LocalDate birthDate) {
        validateBirthDate(birthDate);
        this.birthDate = birthDate;
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (!Objects.isNull(birthDate) && birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("올바른 출생일을 입력해주세요.", ErrorField.of("birthDate", birthDate));
        }
    }

    public static BirthDate from(LocalDate birthDate) {
        return new BirthDate(birthDate);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isEmpty() {
        return Objects.isNull(birthDate);
    }

    public BirthDate updateBirthDate(BirthDate oldBirthDate, BirthDate newBirthDate) {
        if (newBirthDate.isEmpty()) {
            return oldBirthDate;
        }
        return newBirthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BirthDate)) return false;
        BirthDate birthDate1 = (BirthDate) o;
        return getBirthDate().equals(birthDate1.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBirthDate());
    }

    @Override
    public String toString() {
        return birthDate.toString();
    }
}
