package com.example.backend.business.core.common.values;

import java.util.Objects;

public class PageSize {

    private static final int MAX_PAGE_SIZE = 5;

    private final int pageSize;

    public PageSize(Integer pageSize) {
        this.pageSize = getPageSize(pageSize);
    }

    private int getPageSize(Integer pageSize) {
        if (Objects.isNull(pageSize) || pageSize > MAX_PAGE_SIZE) {
            return 0;
        }
        if (pageSize < 0) {
            return 0;
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
