package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class LoginDate {

    private static final long ONE_MONTH = 30;

    private LocalDate lastLoginDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LoginDate() {
        this.lastLoginDate = LocalDate.now();
    }

    private LoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public static LoginDate initLoginDate() {
        return new LoginDate();
    }

    public boolean isOverMonth() {
        return getBefore50DaysLocalDate().isAfter(lastLoginDate);
    }

    private LocalDate getBefore50DaysLocalDate() {
        return LocalDate.now().minusDays(ONE_MONTH);
    }

    public LoginDate update() {
        return new LoginDate(LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginDate)) return false;
        LoginDate loginDate = (LoginDate) o;
        return lastLoginDate.equals(loginDate.lastLoginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastLoginDate);
    }

    @Override
    public String toString() {
        return lastLoginDate.toString();
    }
}
