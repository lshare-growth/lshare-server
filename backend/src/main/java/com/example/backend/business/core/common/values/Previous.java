package com.example.backend.business.core.common.values;

import java.util.Objects;

public class Previous {

    private static final Long MIN_PAGE_NUMBER = 0L;

    private final Long previous;

    public Previous(Long previous) {
        validatePrevious(previous);
        this.previous = previous;
    }

    private void validatePrevious(Long previous) {
        if (!Objects.isNull(previous) && previous < MIN_PAGE_NUMBER) {
            throw new IllegalArgumentException("올바른 페이지 번호를 입력해주세요.");
        }
    }

    public static Previous from(Long previous) {
        return new Previous(previous);
    }

    public Long getPrevious() {
        return previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Previous)) return false;
        Previous previous1 = (Previous) o;
        return previous.equals(previous1.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previous);
    }

    @Override
    public String toString() {
        return previous.toString();
    }
}
