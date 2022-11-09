package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Introduction {

    private static final String INIT_INTRODUCTION = "";
    private static final int MAX_INTRODUCTION_LENGTH = 250;

    private String introduction;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Introduction() {
        this.introduction = INIT_INTRODUCTION;
    }

    private Introduction(String introduction) {
        validateIntroduction(introduction);
        this.introduction = introduction;
    }

    public static Introduction initIntroduction() {
        return new Introduction();
    }

    private void validateIntroduction(String introduction) {
        if (Objects.isNull(introduction)) {
            return;
        }
        if (introduction.length() > MAX_INTRODUCTION_LENGTH) {
            throw new IllegalArgumentException("입력 가능한 자기소개 최대글자수를 초과했습니다.", ErrorField.of("introduction", introduction));
        }
    }

    public static Introduction from(String introduction) {
        return new Introduction(introduction);
    }

    public String getIntroduction() {
        return introduction;
    }

    public boolean isEmpty() {
        return Objects.isNull(introduction)
                || introduction.isBlank()
                || !Objects.isNull(introduction) && introduction.trim().isEmpty();
    }

    public Introduction updateIntroduction(Introduction oldIntroduction, Introduction newIntroduction) {
        if (newIntroduction.isEmpty()) {
            return oldIntroduction;
        }
        return newIntroduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Introduction)) return false;
        Introduction that = (Introduction) o;
        return getIntroduction().equals(that.getIntroduction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntroduction());
    }

    @Override
    public String toString() {
        return introduction;
    }
}
