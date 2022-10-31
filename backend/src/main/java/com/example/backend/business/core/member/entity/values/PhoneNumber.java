package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class PhoneNumber {

    private static final String PHONE_NUMBER_PATTERN = "^\\d{3}-\\d{3,4}-\\d{4}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

    private String phoneNumber;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected PhoneNumber() {
    }

    private PhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber from(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!Objects.isNull(phoneNumber) && !pattern.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("올바른 양식의 번호를 입력해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}
