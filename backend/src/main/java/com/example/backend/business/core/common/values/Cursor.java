package com.example.backend.business.core.common.values;

import java.util.Objects;

public class Cursor {

    private final CursorTarget cursorTarget;
    private final PageSize pageSize;

    public Cursor(Long cursor, Integer pageSize) {
        this.cursorTarget = CursorTarget.from(cursor);
        this.pageSize = PageSize.from(pageSize);
    }

    public static Cursor from(Long cursor, Integer pageSize) {
        return new Cursor(cursor, pageSize);
    }

    public CursorTarget getNextAsValue() {
        return cursorTarget;
    }

    public Long getNext() {
        return cursorTarget.getNext();
    }

    public PageSize getPageSizeAsValue() {
        return pageSize;
    }

    public int getPageSize() {
        return pageSize.getPageSize();
    }

    public boolean isFirstPage() {
        return Objects.isNull(cursorTarget.getNext()) || cursorTarget.getNext() == 0L;
    }

    @Override
    public String toString() {
        return String.format("다음 페이지 번호: %s, 페이지 크기: %s", cursorTarget, pageSize);
    }
}
