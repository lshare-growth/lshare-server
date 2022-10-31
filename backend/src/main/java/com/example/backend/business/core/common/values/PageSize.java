package com.example.backend.business.core.common.values;

import java.util.Objects;

public class PageSize {

    private static final int MAX_PAGE_SIZE = 5;

    private final int pageSize;

    public PageSize(Integer pageSize) {
        validatePageSize(pageSize);
        this.pageSize = getPageSize(pageSize);
    }

    private void validatePageSize(Integer pageSize) {
        if (pageSize < 0) {
            throw new IllegalArgumentException("올바른 페이지 크기를 입력해주세요.");
        }
    }

    private int getPageSize(Integer pageSize) {
        if (Objects.isNull(pageSize) || pageSize > MAX_PAGE_SIZE) {
            return MAX_PAGE_SIZE;
        }
        return pageSize;
    }

    public static PageSize from(Integer pageSize) {
        return new PageSize(pageSize);
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageSize)) return false;
        PageSize pageSize1 = (PageSize) o;
        return pageSize == pageSize1.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageSize);
    }

    @Override
    public String toString() {
        return String.valueOf(pageSize);
    }
}
