package com.example.backend.business.core.common.values;

import java.util.Objects;

public class CursorTarget {

    private final Long next;

    public CursorTarget(Long next) {
        validateNext(next);
        this.next = next;
    }

    private void validateNext(Long next) {
        if (!Objects.isNull(next) && next > Long.MIN_VALUE) {
            throw new IllegalArgumentException("올바른 페이지 번호를 입력해주세요.");
        }
    }

    public static CursorTarget from(Long next) {
        return new CursorTarget(next);
    }

    public Long getNext() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursorTarget)) return false;
        CursorTarget cursorTarget1 = (CursorTarget) o;
        return next.equals(cursorTarget1.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next);
    }

    @Override
    public String toString() {
        return next.toString();
    }
}
